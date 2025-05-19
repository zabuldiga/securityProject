package products.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import products.example.Dto.OrderDto;
import products.example.Dto.OrderItemDto;
import products.example.service.OrderService;


@RestController
@RequestMapping("api/v1/order/")
public class OrderControllers {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto) {

        orderService.createOrder(orderDto);

    }


}
