package products.example;


import org.springframework.data.jpa.domain.Specification;
import products.example.entity.Product;

public class ProductSpecification {

    public static Specification<Product> costGreaterThanOrEqualTo(Integer cost){
        return (root,criteriaQuery,criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"),cost);

    }
    public static Specification<Product> costLessThanOrEqualTo(Integer cost){
        return (root,criteriaQuery,criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"),cost);
    }
//    public static Specification<Product> nameLike(String name){
//        return (root,criteriaQuery,criteriaBuilder) -> criteriaBuilder.like(root.get("name"),name);
//    }
}
