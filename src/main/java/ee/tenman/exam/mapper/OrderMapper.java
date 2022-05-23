package ee.tenman.exam.mapper;

import ee.tenman.exam.domain.Order;
import ee.tenman.exam.domain.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderMapper {
    public Order toOrderEntity(OrderDto orderDto) {
        return new Order(o -> o.setOrderedAt(Instant.parse(orderDto.getOrderedAt())));
    }

    public OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getId())
                .pizzaId(order.getPizza().getId())
                .orderedAt(order.getOrderedAt().toString())
                .userId(order.getUser().getId())
                .build();
    }
}
