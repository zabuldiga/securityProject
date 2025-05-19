package products.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "user_id")
    int user_id;
    @Column(name = "total_price")
    int total_price;
    @Column(name = "address")
    String address;
    @Column(name = "phone")
    String phone;
    @ManyToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItems> items = new ArrayList<>();

}
