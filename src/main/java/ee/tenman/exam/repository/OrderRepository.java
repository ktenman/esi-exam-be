package ee.tenman.exam.repository;

import ee.tenman.exam.domain.Order;
import ee.tenman.exam.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
