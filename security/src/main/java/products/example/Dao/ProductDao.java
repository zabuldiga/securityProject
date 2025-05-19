package products.example.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import products.example.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDao implements ProductDaoInf {
    @PersistenceContext
    private EntityManager entityManager;


    public Product findById(Long id) {
        Product p = entityManager.find(Product.class, id);
        return p;
    }


    @Transactional
    public void deleteById(Long id) {
        Product p = findById(id);
        entityManager.remove(p);
    }


    public List<Product> findALl() {
        return entityManager.createQuery("SELECT p FROM Product p ORDER BY p.id ASC", Product.class)
                .getResultList();    }

    @Transactional
    public void changeCost(Long productId, Integer delta) {
        Product p = findById(productId);
        p.setCost(p.getCost() + delta);

    }


}


