package products.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import products.example.entity.OrderItems;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String product_title;
    private String username;
    private String address;
    private String phone;
    private int total_price;
    private List<OrderItems> items;
}
