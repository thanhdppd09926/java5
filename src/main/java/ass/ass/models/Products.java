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
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Khóa chính, tự động tăng

    private String name; // NVARCHAR(50)
    private String image; // NVARCHAR(50)
    private float price; // FLOAT
    private Date createDate; // DATE
    private boolean available; // BIT

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false) // Khóa ngoại đến bảng Categories
    private Categories category;
}