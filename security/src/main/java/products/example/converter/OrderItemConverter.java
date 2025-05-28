package products.example.converter;



import org.springframework.stereotype.Component;
import products.example.Dto.OrderItemDto;
import products.example.entity.OrderItems;

@Component
public class OrderItemConverter {

    public OrderItemDto toDto(OrderItems entity) {
        OrderItemDto dto = new OrderItemDto();
        dto.setProductId(entity.getId());
        dto.setProductId(entity.getProduct().getId()); // предполагается getProduct().getId()
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getTotal_price());
        return dto;
    }

    public OrderItems toEntity(OrderItemDto dto) {
        OrderItems entity = new OrderItems();
        entity.setId(dto.getProductId());
        entity.setQuantity(dto.getQuantity());
        entity.setTotal_price(dto.getPrice());

        // Важно: сеттинг продукта может потребовать загрузки из БД в сервисе
        return entity;
    }
}

