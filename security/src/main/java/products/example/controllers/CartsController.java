package products.example.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import products.example.Dto.Cart;
import products.example.repository.ProductRepository;
import products.example.service.CartService;
import products.example.service.ProductService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartsController {

    private final CartService cartService;


    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }


    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id) {
        cartService.addProductByIdToCart(id);

    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.getCurrentCart().clear();

    }

    @GetMapping("/delete/{productId}")
    public Cart delete(@PathVariable Long productId){
        cartService.delete(productId);
        return cartService.getCurrentCart();
    }

}
