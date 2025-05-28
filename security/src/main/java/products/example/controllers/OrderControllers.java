    package products.example.controllers;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import products.example.Dto.OrderDto;
    import products.example.Dto.OrderItemDto;
    import products.example.converter.OrderConverter;
    import products.example.entity.Order;
    import products.example.service.OrderService;

    import java.security.Principal;
    import java.util.List;
    import java.util.stream.Collectors;


    @RestController
    @RequestMapping("api/v1/order/")
    public class OrderControllers {
        @Autowired
        private OrderService orderService;
        @Autowired
        private OrderConverter orderC;

        @PostMapping
        public void createOrder(@RequestBody OrderDto orderDto) {
            orderService.createOrder(orderDto);

        }

            @GetMapping
            public List<OrderDto> getCurrentUserOrders(Principal principal) {
                return orderService.findOrderByUsername(principal.getName()).stream().map(orderC::toDto).collect(Collectors.toList());

            }
    }
