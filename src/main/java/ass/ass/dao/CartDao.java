package ass.ass.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ass.ass.models.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {

}
