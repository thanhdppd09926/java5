package ass.ass.repository;

import ass.ass.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts, String> {
    Accounts findByUsername(String username); // Đã có trong LoginController
}