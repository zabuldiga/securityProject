package products.example.Dao;

import products.example.entity.Product;

import java.util.List;

public interface ProductDaoInf {
    List<Product> findALl();
    void deleteById(Long id);
    Product findById(Long Id);
    void changeCost(Long productId, Integer delta);
}
