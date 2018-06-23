package ro.kiuny.practic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity<Long> implements Serializable {

    @Column(unique = true)
    private LocalDateTime date;
    private Integer totalPrice;

    @Embedded
    private ContactInfo contactInfo;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "lineItemPk.order")
    private Set<LineItem> lineItems;

}
