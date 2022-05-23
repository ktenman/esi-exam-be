package ee.tenman.exam.service;

import ee.tenman.exam.domain.Role;
import ee.tenman.exam.domain.RoleType;
import ee.tenman.exam.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService;

    @Test
    void findAllRolesByRoleTypes() {
        Role role = new Role(RoleType.ROLE_ADMIN);
        Mockito.when(roleRepository.findAllByRoleTypeIn(Mockito.any())).thenReturn(List.of(role));

        Set<Role> roles = roleService.findAllRolesByRoleTypes(Set.of(RoleType.ROLE_ADMIN));

        Assertions.assertThat(roles).hasSize(1)
                .contains(role);
    }
}
