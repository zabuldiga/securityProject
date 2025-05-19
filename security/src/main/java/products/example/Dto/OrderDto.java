package products.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private String username;
    private String phone;
    private String address;
    private int total_price;
    private List<OrderItemDto> items;
}
