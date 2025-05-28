package products.example.converter;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import products.example.Dto.OrderDto;
import products.example.entity.Order;
import products.example.entity.OrderItems;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

    public OrderDto toDto(Order order) {
        if (order == null) return null;

        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setUsername(order.getUser().getUsername()); // предполагается, что есть getUsername()
        dto.setPhone(order.getPhone());
        dto.setAddress(order.getAddress());
        dto.setTotal_price(order.getTotal_price());
        dto.setItems(order.getItems().stream()
                .map(orderItemConverter::toDto)
                .collect(Collectors.toList()));
        return dto;
    }

    public Order toEntity(OrderDto dto) {
        if (dto == null) return null;

        Order order = new Order();
        order.setId(dto.getId());
        order.setPhone(dto.getPhone());
        order.setAddress(dto.getAddress());
        order.setTotal_price(dto.getTotal_price());

        // Items
        List<OrderItems> items = dto.getItems().stream()
                .map(orderItemDto -> {
                    OrderItems entity = orderItemConverter.toEntity(orderItemDto);
                    entity.setOrder(order); // важно!
                    return entity;
                })
                .collect(Collectors.toList());
        order.setItems(items);

        return order;
    }
}
