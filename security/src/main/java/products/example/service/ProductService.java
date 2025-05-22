package products.example.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import products.example.Dto.ProductDto;
import products.example.ProductSpecification;
import products.example.entity.Basket;
import products.example.entity.Product;
import products.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long findIdByName(String name){
         return   productRepository.findIdByName(name);
    }
    public Product findByName(String name){
        return productRepository.findByName(name);

    }
    public Page<Product> find(Integer minCost, Integer maxCost, Integer page) {
        Specification<Product> spec = Specification.where(null);

        if (minCost != null) {
            spec = spec.and(ProductSpecification.costGreaterThanOrEqualTo(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecification.costLessThanOrEqualTo(maxCost));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 5, Sort.by("id").ascending()));
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);

    }

    public List<Product> showAll() {
        return productRepository.showAll();

    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    public List<Product> showAllMin(Integer minCost) {
        return productRepository.showAllMin(minCost);

    }

    public List<Product> showAllMax(Integer maxCost) {
        return productRepository.showAllMax(maxCost);

    }

    public List<Product> showAllBetween(Integer minCost, Integer maxCost) {
        return productRepository.showAllBetween(minCost, maxCost);

    }

    public void addProduct(ProductDto productDto) {


        productRepository.save(new Product(productDto.getName(), productDto.getCost()));

    }

    public void changeCost(Long id, Integer delta) {
        productRepository.changeProductCost(delta, id);

    }

    public List<Basket> showBasket() {
        return productRepository.showAllBasket();
    }

}
