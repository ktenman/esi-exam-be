package ee.tenman.exam.mapper;

import ee.tenman.exam.domain.Pizza;
import ee.tenman.exam.domain.dto.PizzaDto;
import org.springframework.stereotype.Service;

@Service
public class PizzaMapper {

    public PizzaDto toPizzaDto(Pizza pizza) {
        return PizzaDto.builder()
                .name(pizza.getName())
                .type(pizza.getType())
                .pizzaId(pizza.getId())
                .build();
    }

    public Pizza toPizzaEntity(PizzaDto pizzaDto) {
        return new Pizza(p -> {
            p.setName(pizzaDto.getName());
            p.setType(pizzaDto.getType());
        });
    }
}
