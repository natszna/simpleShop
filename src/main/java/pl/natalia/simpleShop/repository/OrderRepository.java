package pl.natalia.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.natalia.simpleShop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(long orderId);
}
