package ro.kiuny.practic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.kiuny.practic.model.LineItem;

import java.util.List;

public interface LineItemRepository extends JpaRepository<LineItem, LineItem.LineItemPk> {

    @Query("select i from LineItem i where i.lineItemPk.order.status = ro.kiuny.practic.model.OrderStatus.CART")
    List<LineItem> getCartItems();

}
