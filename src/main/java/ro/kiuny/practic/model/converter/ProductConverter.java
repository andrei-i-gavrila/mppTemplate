package ro.kiuny.practic.model.converter;

import org.springframework.stereotype.Component;
import ro.kiuny.practic.dto.ProductDto;
import ro.kiuny.practic.model.Product;

@Component
public class ProductConverter {

    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }

}
