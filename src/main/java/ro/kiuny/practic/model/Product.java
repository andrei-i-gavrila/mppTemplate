package ro.kiuny.practic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product extends BaseEntity<Long> implements Serializable {

    @Column(unique = true)
    private String name;
    private Integer price;
    private Integer stock;

    @OneToMany(mappedBy = "lineItemPk.product")
    private Set<LineItem> lineItems;

}
