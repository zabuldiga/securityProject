package products.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import products.example.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
