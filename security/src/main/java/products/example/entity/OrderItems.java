package products.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_products_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
    @Column(name = "user_id")
    int user_id;
    @Column(name = "quantity")
    int quantity;
    @Column(name = "price_per_product")
    int pricePerProduct;
    @Column(name = "price")
    int total_price;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
