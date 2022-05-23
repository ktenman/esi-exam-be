package ee.tenman.exam.repository;

import ee.tenman.exam.domain.Role;
import ee.tenman.exam.domain.Tour;
import ee.tenman.exam.domain.enums.RoleType;
import ee.tenman.exam.domain.enums.TourStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAllByStatusIn(Collection<TourStatus> statuses);
}
