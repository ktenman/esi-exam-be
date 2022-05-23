package ee.tenman.exam.repository;

import ee.tenman.exam.domain.Pizza;
import ee.tenman.exam.domain.Role;
import ee.tenman.exam.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
