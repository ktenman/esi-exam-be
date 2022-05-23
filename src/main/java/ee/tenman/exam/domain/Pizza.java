package ee.tenman.exam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ee.tenman.exam.domain.enums.PizzaType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static ee.tenman.exam.domain.enums.PizzaType.THICK;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pizza {

    private static final String SEQ_PIZZA = "seq_pizza";
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_PIZZA)
    @SequenceGenerator(name = SEQ_PIZZA, sequenceName = SEQ_PIZZA, allocationSize = 1)
    private Long id;
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private PizzaType type = THICK;

    @JsonIgnore
    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
    @OrderBy("id DESC")
    private List<Order> orders = new ArrayList<>();

    public Pizza(Consumer<Pizza> builder) {
        builder.accept(this);
    }
}
