package ro.kiuny.practic.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lineitem")
public class LineItem {

    @EmbeddedId
    LineItemPk lineItemPk;

    private Integer quantity;


    public LineItem(Product product) {
        this.lineItemPk = new LineItemPk(product, null);
        this.quantity = 1;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class LineItemPk implements Serializable {
        @JoinColumn(name = "product_id")
        @ManyToOne
        private Product product;
        @JoinColumn(name = "order_id", nullable = false)
        @ManyToOne(optional = true)
        private Order order;
    }
}
