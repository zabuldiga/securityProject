package products.example.Dto;

import products.example.entity.Product;

public class ProductDto {

    Long id;
    String name;

    int cost;

    Integer delta;

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public ProductDto(Product product) {
        this.name = product.getName();
        this.cost = product.getCost();
        this.id = product.getId();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ProductDto() {
    }
}
