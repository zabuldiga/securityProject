package products.example.service;

import com.example.security.services.UserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import products.example.Dto.OrderDto;
import products.example.Dto.OrderItemDto;
import products.example.entity.Order;
import products.example.entity.OrderItems;
import products.example.repository.OrderItemsRepository;
import products.example.repository.OrderRepository;

import java.util.stream.Collectors;

@Service
public class OrderService {

    private UserDetailsServices userDetailsServices;
    private ProductService productService;
    private OrderRepository orderRepository;
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    public OrderService(UserDetailsServices userDetailsServices, ProductService productService, OrderRepository orderRepository, OrderItemsRepository orderItemsRepository) {
        this.userDetailsServices = userDetailsServices;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;
    }


    public void createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setAddress(orderDto.getAddress());
        order.setPhone(orderDto.getPhone());
        order.setTotal_price(orderDto.getTotal_price());
        order.setUser_id(userDetailsServices.findIdByUser(orderDto.getUsername()));

        Order saveOrder = orderRepository.save(order);

        for (OrderItemDto o : orderDto.getItems()){
            OrderItems orderItems = new OrderItems();
            orderItems.setProduct_id(Math.toIntExact(productService.findIdByName(o.getProductTitle())));
            orderItems.setUser_id(saveOrder.getUser_id());
            orderItems.setOrder(saveOrder);
            orderItems.setQuantity(o.getQuantity());
            orderItems.setPricePerProduct(o.getPricePerProduct());
            orderItems.setTotal_price(o.getPrice());
            orderItemsRepository.save(orderItems);
        }

    }
}
