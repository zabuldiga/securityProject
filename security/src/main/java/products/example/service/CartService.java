package products.example.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import products.example.Dto.Cart;
import products.example.Exception.ResourceNotFoundException;
import products.example.entity.Product;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    public final ProductService productService;

    @PostConstruct
    public void init() {
        cart = new Cart();

    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void clear() {
        getCurrentCart().clear();

    }


    public void addProductByIdToCart(Long productId) {
        if (!getCurrentCart().addProductById(productId)) {
            Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину.Продукт не найден , id : " + productId));
            getCurrentCart().addProduct(product);
        }


    }
}
