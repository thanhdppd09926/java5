package ass.ass.services;

import ass.ass.models.Accounts;
import ass.ass.models.Cart;
import ass.ass.models.OrderDetails;
import ass.ass.models.Orders;
import ass.ass.models.Products;
import ass.ass.repository.AccountRepository;
import ass.ass.repository.CartRepository;
import ass.ass.repository.OrderDetailsRepository;
import ass.ass.repository.OrderRepository;
import ass.ass.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    // Lấy tất cả đơn hàng
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    // Lấy đơn hàng theo username (dành cho người dùng thường)
    public List<Orders> getOrdersByUsernameList(String username) {
        Accounts account = new Accounts();
        account.setUsername(username);
        return orderRepository.findByAccount(account);
    }

    // Lấy đơn hàng theo ID
    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // Lấy chi tiết đơn hàng theo orderId
    public List<OrderDetails> getOrderDetailsByOrderId(Long orderId) {
        Orders order = getOrderById(orderId);
        return orderDetailsRepository.findAll().stream()
                .filter(detail -> detail.getOrder().getId() == orderId)
                .toList();
    }

    public Orders checkout(String username, String address) {
        Accounts account = accountRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại!"));
        List<Cart> cartItems = cartRepository.findByAccount(account);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Giỏ hàng trống!");
        }

        Orders order = Orders.builder()
                .createDate(new Date(System.currentTimeMillis()))
                .address(address)
                .account(account)
                .build();
        order = orderRepository.save(order);

        for (Cart item : cartItems) {
            OrderDetails detail = OrderDetails.builder()
                    .order(order)
                    .product(item.getProduct())
                    .price(item.getProduct().getPrice())
                    .quantity(item.getQuantity())
                    .build();
            orderDetailsRepository.save(detail);
        }

        cartService.clearCart(username);
        return order;
    }

    public List<Orders> getOrdersByUsername(String username) {
        Accounts account = accountRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại!"));
        return orderRepository.findByAccount(account);
    }

    public void buyNow(String username, int productId, int quantity, String address) {
        // Lấy thông tin tài khoản và sản phẩm
        Accounts account = accountRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại!"));
        Products product = productRepository.findById((Integer) productId) // Chuyển int thành long
                .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại!"));

        // Tạo đơn hàng mới
        Orders order = Orders.builder()
                .createDate(new Date(System.currentTimeMillis()))
                .address(address)
                .account(account)
                .build();
        order = orderRepository.save(order);

        // Tạo chi tiết đơn hàng
        OrderDetails detail = OrderDetails.builder()
                .order(order)
                .product(product)
                .price(product.getPrice())
                .quantity(quantity)
                .build();
        orderDetailsRepository.save(detail);
    }
}