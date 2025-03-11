package ass.ass.repository;

import ass.ass.models.Accounts;
import ass.ass.models.Cart;
import ass.ass.models.Products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByAccount(Accounts account);

    // Products findProductById(Long productId);

    // List<Cart> findAll();

    // Cart findByProduct(Products product);

    // Optional<Cart> findByUserAndProduct(Accounts user, Products product);

    Cart findByAccountAndProductId(Accounts account, int productId);

    void deleteByAccountAndProductId(Accounts account, int productId);
}