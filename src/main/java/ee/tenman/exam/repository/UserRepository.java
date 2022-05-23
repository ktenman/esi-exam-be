package ee.tenman.exam.repository;

import ee.tenman.exam.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @EntityGraph(value = "graph.User.roles")
    Optional<User> findTopByUsername(String username);
}
