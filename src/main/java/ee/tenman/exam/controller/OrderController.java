package ee.tenman.exam.controller;

import ee.tenman.exam.domain.dto.OrderDto;
import ee.tenman.exam.domain.dto.PizzaDto;
import ee.tenman.exam.service.OrderService;
import ee.tenman.exam.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public OrderDto save(@Valid @RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }
}
