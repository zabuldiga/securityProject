package products.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "product_id")
    int product_id;
    @Column(name = "user_id")
    int user_id;
//    @Column(name = "order_id")
//    int order_id;
    @Column(name = "quantity")
    int quantity;
    @Column(name = "price_per_product")
    int pricePerProduct;
    @Column(name = "price")
    int total_price;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

}
