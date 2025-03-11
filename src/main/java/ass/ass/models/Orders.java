package ass.ass.models;

import java.sql.Date;

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
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Khóa chính, tự động tăng

    private Date createDate; // DATETIME
    private String address; // NVARCHAR(100)

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false) // Khóa ngoại đến bảng Accounts
    private Accounts account;

}
