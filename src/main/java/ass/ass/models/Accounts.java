package ass.ass.models;

import org.hibernate.validator.constraints.Email;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Accounts {
    @Id
    private String username; // Khóa chính, NVARCHAR(50)

    @NotBlank(message = "Không được để trống!")
    private String password; // NVARCHAR(50)
    private String fullname; // NVARCHAR(50)

    @Email(message = "Không đúng định dạng Email!")
    private String email; // NVARCHAR(50), UNIQUE
    private String photo; // NVARCHAR(50)
    private boolean activated; // BIT
    private boolean admin; // BIT
}