package ass.ass.controller;

import ass.ass.dao.CartDao;
import ass.ass.models.Accounts;
import ass.ass.models.Cart;
import ass.ass.repository.CartRepository;
import ass.ass.services.CartService;
import ass.ass.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartDao cartDao;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Accounts user = (Accounts) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Cart> cartItems = cartService.viewCart(user.getUsername());
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("username", user.getUsername());
        return "index/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam String username, @RequestParam int productId, @RequestParam int quantity,
            HttpSession session) {
        Accounts user = (Accounts) session.getAttribute("user");
        System.out.println("Session user: " + (user != null ? user.getUsername() : "null"));
        System.out.println("Request username: " + username);
        if (user == null) {
            System.out.println("User not logged in, redirecting to login");
            return "redirect:/login";
        }
        if (!user.getUsername().equals(username)) {
            System.out.println("Username mismatch, redirecting to login");
            return "redirect:/login";
        }
        try {
            cartService.addToCart(username, productId, quantity);
            return "redirect:/home";
        } catch (Exception e) {
            System.out.println("Error adding to cart: " + e.getMessage());
            return "redirect:/home?error=add_failed";
        }
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam String username, @RequestParam int productId, @RequestParam int quantity,
            HttpSession session) {
        Accounts user = (Accounts) session.getAttribute("user");
        if (user == null || !user.getUsername().equals(username)) {
            return "redirect:/login";
        }
        cartService.updateCart(username, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String deleteProduct(@PathVariable long id) {
        cartDao.deleteById(id);
        return "redirect:/cart?username=";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String username, @RequestParam String address, HttpSession session) {
        Accounts user = (Accounts) session.getAttribute("user");
        if (user == null || !user.getUsername().equals(username)) {
            return "redirect:/login";
        }
        orderService.checkout(username, address);
        return "redirect:/orders?username=" + username;
    }

}