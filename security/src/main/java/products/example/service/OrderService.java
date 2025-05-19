package products.example.service;

import com.example.security.entity.User;
import com.example.security.services.UserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import products.example.Dto.OrderDto;
import products.example.Dto.OrderItemDto;
import products.example.entity.Order;
import products.example.entity.OrderItems;
import products.example.repository.OrderItemsRepository;
import products.example.repository.OrderRepository;

import java.util.Iterator;

@Service
public class OrderService {

    private ProductService productService;

    private UserDetailsServices userDetailsServices;

    private OrderRepository orderRepository;

    private OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderService(ProductService productService, UserDetailsServices userDetailsServices, OrderRepository orderRepository, OrderItemsRepository orderItemsRepository) {
        this.productService = productService;
        this.userDetailsServices = userDetailsServices;
        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;

    }

    public void createOrdersItems(OrderDto orderDto, int id) {
        int user_id = userDetailsServices.findIdByUser(orderDto.getUsername());
        int product_id = Math.toIntExact(productService.findIdByName(orderDto.getProduct_title()));
        for (OrderItems o : orderDto.getItems()) {
            OrderItems orderItems = new OrderItems();
            orderItems.setOrder_id(id);
            orderItems.setProduct_id(product_id);
            orderItems.setUser_id(user_id);
            orderItems.setQuantity(o.getQuantity());
            orderItems.setPricePerProduct(o.getPricePerProduct());
            orderItems.setTotal_price(o.getTotal_price());

            
            orderItemsRepository.save(orderItems);
        }
    }

    public void createOrder(OrderDto orderDto) {
        int user_id = userDetailsServices.findIdByUser(orderDto.getUsername());


        Order order = new Order();

        order.setUser_id(user_id);
        order.setPhone(orderDto.getPhone());
        order.setAddress(orderDto.getAddress());
        order.setTotal_price(orderDto.getTotal_price());



        orderRepository.save(order);
        createOrdersItems(orderDto, Math.toIntExact(order.getId()));
    }
}
