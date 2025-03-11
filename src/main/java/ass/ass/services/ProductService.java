package ass.ass.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ass.ass.dao.ProductDao;
import ass.ass.models.Products;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Products> getAllProducts() {
        return productDao.findAll();
    }

    public Products getProductById(int id) {
        return productDao.findById(id).orElse(null);
    }

    public Products saveProduct(Products product) {
        return productDao.save(product);
    }

    public void deleteProduct(int id) {
        productDao.deleteById(id);
    }
}