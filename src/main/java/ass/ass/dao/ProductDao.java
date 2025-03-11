package ass.ass.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ass.ass.models.Products;

@Repository
public interface ProductDao extends JpaRepository<Products, Integer> {

    List<Products> findByCategoryId(String categoryId);

    Products findByName(String name);

    List<Products> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR p.category.id = :categoryId")
    List<Products> searchProducts(@Param("keyword") String keyword, @Param("categoryId") String categoryId);

    // @Query("FROM Products o Where o.categoryId = :categoryId")
    // List<Products> findByCategoryIdQuery(String categoryId);
}
