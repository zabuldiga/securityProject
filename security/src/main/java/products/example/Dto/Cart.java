package products.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import products.example.entity.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (addProductById(product.getId())) {
            return;
        }
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0;

    }


    public boolean addProductById(Long id) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(id)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }

        }
        return false;
    }





    public void decreaseProduct(Long id) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(id)) {
                o.changeQuantity(-1);
                recalculate();
                if (o.getQuantity() <= 0) {
                    iter.remove();
                    recalculate();
                    return;
                }

            }
        }

    }

    public void removeProduct(Long id) {
        items.removeIf(o -> o.getProductId().equals(id));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (OrderItemDto o : items) {
            totalPrice += o.getPrice();
        }

    }
}
