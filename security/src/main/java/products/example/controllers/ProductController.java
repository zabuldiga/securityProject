package products.example.controllers;

import org.springframework.http.ResponseEntity;
import products.example.entity.Basket;
import products.example.Exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import products.example.entity.Product;
import products.example.Dto.ProductDto;
import products.example.service.ProductService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products/")
public class ProductController {


    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("user-info")
    public ResponseEntity<?> getUserInfo(Principal principal) {
        return ResponseEntity.ok(Map.of("username", principal.getName()));
    }


    @GetMapping("{id}")
    public Product find(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found , id: " + id));
    }


    @PostMapping
    public void add(@RequestBody ProductDto newProductDtoJson) {
        System.out.println("Полученные данные: " + newProductDtoJson);
        productService.addProduct(newProductDtoJson);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);

    }

    @PatchMapping("change_cost")
    public void changeCost(@RequestParam Long id, @RequestParam Integer delta) {
        productService.changeCost(id,delta);

    }

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public Page<ProductDto> showAll(@RequestParam(required = false) Integer minCost,
                                    @RequestParam(required = false) Integer maxCost,
                                    @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        if (page < 1) {
            page = 1;

        }
        Page<Product> products = productService.find(minCost, maxCost, page);
        if(products.isEmpty()){
            new ResourceNotFoundException("Products not found");
        }
        return productService.find(minCost, maxCost, page).map(ProductDto::new);
    }

    @GetMapping("basket")
    public List<Basket> showBasket(){
        return productService.showBasket();

    }


}
