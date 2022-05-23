package ee.tenman.exam.controller;

import ee.tenman.exam.domain.Pizza;
import ee.tenman.exam.domain.dto.PizzaDto;
import ee.tenman.exam.payload.request.LoginRequest;
import ee.tenman.exam.payload.response.JwtResponse;
import ee.tenman.exam.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
@AllArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PizzaDto save(@Valid @RequestBody PizzaDto pizzaDto) {
        return pizzaService.save(pizzaDto);
    }

    @GetMapping
    public List<PizzaDto> findAll() {
        return pizzaService.findAll();
    }
}
