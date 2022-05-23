package ee.tenman.exam.service;

import ee.tenman.exam.domain.Pizza;
import ee.tenman.exam.domain.dto.PizzaDto;
import ee.tenman.exam.exception.GeneralException;
import ee.tenman.exam.mapper.PizzaMapper;
import ee.tenman.exam.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record PizzaService(PizzaRepository pizzaRepository, PizzaMapper pizzaMapper) {
    public PizzaDto save(PizzaDto pizzaDto) {
        Pizza pizza = pizzaMapper.toPizzaEntity(pizzaDto);
        Pizza savePizza = pizzaRepository.save(pizza);
        return pizzaMapper.toPizzaDto(savePizza);
    }

    public Pizza findPizzaById(Long pizzaId) {
        return pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new GeneralException(String.format("Pizza with id %s not found", pizzaId)));
    }

    public List<PizzaDto> findAll() {
        return pizzaRepository.findAll()
                .stream().map(pizzaMapper::toPizzaDto)
                .toList();
    }
}
