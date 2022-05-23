package ee.tenman.exam.domain;

import ee.tenman.exam.domain.enums.RoleType;
import ee.tenman.exam.domain.enums.TourStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import java.time.Instant;
import java.util.function.Consumer;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tour {
    private static final String SEQ_TOUR = "seq_tour";
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQ_TOUR)
    @SequenceGenerator(name = SEQ_TOUR, sequenceName = SEQ_TOUR, allocationSize = 1)
    private Long id;
    @NotNull
    private String name;
    private String location;
    private Instant beginDate;
    private Instant endDate;
    @Enumerated(EnumType.STRING)
    private TourStatus status = TourStatus.AVAILABLE;

    public Tour(Consumer<Tour> builder) {
        builder.accept(this);
    }
}
