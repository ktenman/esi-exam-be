package ee.tenman.exam.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.Instant;
import java.util.function.Consumer;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    private static final String SEQ_ORDERS = "seq_orders";
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_ORDERS)
    @SequenceGenerator(name = SEQ_ORDERS, sequenceName = SEQ_ORDERS, allocationSize = 1)
    private Long id;

    private Instant orderedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    private Pizza pizza;

    public Order(Consumer<Order> builder) {
        builder.accept(this);
    }
}
