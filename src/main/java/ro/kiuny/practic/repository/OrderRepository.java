package ro.kiuny.practic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.kiuny.practic.model.Order;
import ro.kiuny.practic.model.OrderStatus;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>
{
    Optional<Order> findByStatus(OrderStatus status);

}
