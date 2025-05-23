package products.example.controllers;

import com.example.security.dto.StringResponse;
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

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartsController {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/{uuid}")
    public Cart getCart(Principal principal, @PathVariable String uuid) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(Principal principal, @PathVariable String uuid) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
//    @GetMapping
//    public Cart getCurrentCart() {
//        return cartService.getCurrentCart();
//    }
//
//
//    @GetMapping("/add/{id}")
//    public void addProductToCart(@PathVariable Long id) {
//        cartService.addProductByIdToCart(id);
//
//    }
//
//    @GetMapping("/clear")
//    public void clearCart() {
//        cartService.getCurrentCart().clear();
//
//    }
//
//    @GetMapping("/delete/{productId}")
//    public Cart delete(@PathVariable Long productId){
//        cartService.delete(productId);
//        return cartService.getCurrentCart();
//    }

}
