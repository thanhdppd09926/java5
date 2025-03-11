package ass.ass.repository;

import ass.ass.models.Accounts;
import ass.ass.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByAccount(Accounts account);
}