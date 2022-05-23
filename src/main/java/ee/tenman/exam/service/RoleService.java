package ee.tenman.exam.service;

import ee.tenman.exam.domain.Role;
import ee.tenman.exam.domain.enums.RoleType;
import ee.tenman.exam.exception.RoleNotFoundException;
import ee.tenman.exam.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public record RoleService(RoleRepository roleRepository) {
    public Set<Role> findAllRolesByRoleTypes(Collection<RoleType> roleTypes) {
        List<Role> roleList = roleRepository.findAllByRoleTypeIn(roleTypes);
        if (roleList.isEmpty()) {
            throw new RoleNotFoundException();
        }
        return Set.copyOf(roleList);
    }
}
