package products.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import products.example.entity.OrderItems;
@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
}
