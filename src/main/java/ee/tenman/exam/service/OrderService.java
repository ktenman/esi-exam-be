package ee.tenman.exam.service;

import ee.tenman.exam.domain.Order;
import ee.tenman.exam.domain.Pizza;
import ee.tenman.exam.domain.User;
import ee.tenman.exam.domain.dto.OrderDto;
import ee.tenman.exam.mapper.OrderMapper;
import ee.tenman.exam.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public record OrderService(
        OrderRepository orderRepository,
        OrderMapper orderMapper,
        PizzaService pizzaService,
        UserService userService
) {
    public OrderDto save(OrderDto orderDto) {
        Order order = orderMapper.toOrderEntity(orderDto);
        User user = userService.getUserWithRoles();
        Pizza pizza = pizzaService.findPizzaById(orderDto.getPizzaId());
        order.setPizza(pizza);
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderDto(savedOrder);
    }
}
