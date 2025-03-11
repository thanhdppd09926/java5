package ass.ass.services;

import ass.ass.models.Accounts;
import ass.ass.models.Cart;
import ass.ass.models.Products;
import ass.ass.repository.AccountRepository;
import ass.ass.repository.CartRepository;
import ass.ass.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Cart> viewCart(String username) {
        Accounts account = accountRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại!"));
        return cartRepository.findByAccount(account);
    }

    public void addToCart(String username, int productId, int quantity) {
        Accounts account = accountRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại!"));
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại!"));

        if (!product.isAvailable()) {
            throw new IllegalStateException("Sản phẩm không có sẵn!");
        }

        Cart existingItem = cartRepository.findByAccountAndProductId(account, productId);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartRepository.save(existingItem);
        } else {
            Cart newItem = Cart.builder()
                    .account(account)
                    .product(product)
                    .quantity(quantity)
                    .build();
            cartRepository.save(newItem);
        }
        System.out.println("Product added to cart successfully for user: " + username);
    }

    public void updateCart(String username, int productId, int quantity) {
        Accounts account = accountRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại!"));
        Cart item = cartRepository.findByAccountAndProductId(account, productId);
        if (item != null) {
            if (quantity <= 0) {
                cartRepository.delete(item);
            } else {
                item.setQuantity(quantity);
                cartRepository.save(item);
            }
        }
    }

    // public void removeFromCart(long cartId, String username) {
    // Cart cartItem = cartRepository.findById(cartId)
    // .orElseThrow(() -> new IllegalArgumentException("Mục giỏ hàng không tồn
    // tại!"));
    // Accounts account = accountRepository.findById(username)
    // .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn
    // tại!"));
    // if (!cartItem.getUsername().equals(username)) { // Giả định Cart có trường
    // username
    // throw new IllegalArgumentException("Mục giỏ hàng không thuộc về người dùng
    // này!");
    // }
    // System.out.println("Removing cart item: " + cartItem);
    // cartRepository.delete(cartItem);
    // }

    public void clearCart(String username) {
        Accounts account = accountRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại!"));
        List<Cart> cartItems = cartRepository.findByAccount(account);
        cartRepository.deleteAll(cartItems);
    }
}