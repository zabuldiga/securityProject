package products.example.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import products.example.entity.Basket;
import products.example.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {



    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.cost = p.cost + :delta WHERE p.id  = :id")
    void changeProductCost(@Param("delta") Integer delta, @Param("id") Long id);

    @Query("select p from Product p order by p.id ASC")
    List<Product> showAll();

    


    @Query("select p from Product p where p.cost > :minCost")
    List<Product> showAllMin(@Param("minCost") Integer minCost);


    @Query("select p from Product p where p.cost < :maxCost")
    List<Product> showAllMax(@Param("maxCost") Integer maxCost);

    @Query("select p from Product p where  p.cost between :minCost and :maxCost ")
    List<Product> showAllBetween(@Param("minCost") Integer minCost, @Param("maxCost") Integer maxCost);

    @Query("select b from Basket b ")
    List<Basket> showAllBasket();

    @Query("SELECT p.id FROM Product p WHERE p.name = :product_name")
    Long findIdByName(@Param("product_name") String name);


}
