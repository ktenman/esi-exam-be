package ee.tenman.exam.repository;

import ee.tenman.exam.domain.RoleType;
import ee.tenman.exam.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);

    List<Role> findAllByRoleTypeIn(Collection<RoleType> roleSet);
}
