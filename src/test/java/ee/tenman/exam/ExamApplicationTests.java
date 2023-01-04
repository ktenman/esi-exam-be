package ee.tenman.exam;

import ee.tenman.exam.domain.Tour;
import ee.tenman.exam.domain.enums.TourStatus;
import ee.tenman.exam.repository.TourRepository;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class 	ExamApplicationTests extends IntegrationTestBase {

	@Resource
	TourRepository tourRepository;

	@Test
	void saveTour1() {
		assertThat(tourRepository.findAll()).isEmpty();
		Tour tour = new Tour(t -> {
			t.setName("Tour 1");
			t.setLocation("Kaunas");
			t.setBeginDate(Instant.now());
			t.setEndDate(Instant.now().plusSeconds(9999));
			t.setStatus(TourStatus.AVAILABLE);
		});

		tourRepository.save(tour);

		assertThat(tourRepository.findAll()).isNotEmpty();
	}

	@Test
	void saveTour2() {
		assertThat(tourRepository.findAll()).isEmpty();
		Tour tour = new Tour(t -> {
			t.setName("Tour 2");
			t.setLocation("Vilnius");
			t.setBeginDate(Instant.now());
			t.setEndDate(Instant.now().plusSeconds(9999));
			t.setStatus(TourStatus.AVAILABLE);
		});

		tourRepository.save(tour);

		assertThat(tourRepository.findAll()).isNotEmpty();
	}

}
