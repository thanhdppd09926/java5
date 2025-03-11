package ass.ass.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Khóa chính, tự động tăng

    @ManyToOne
    @JoinColumn(name = "username", nullable = false) // Khóa ngoại đến bảng Accounts
    private Accounts account;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false) // Khóa ngoại đến bảng Products
    private Products product;

    private int quantity; // Số lượng sản phẩm trong giỏ hàng
}