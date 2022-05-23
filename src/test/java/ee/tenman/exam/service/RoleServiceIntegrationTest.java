package ee.tenman.exam.service;

import ee.tenman.exam.IntegrationTestBase;
import ee.tenman.exam.domain.Role;
import ee.tenman.exam.domain.enums.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Set;

class RoleServiceIntegrationTest extends IntegrationTestBase {

    @Resource
    RoleService roleService;

    @Test
    void findAllRolesByRoleTypes() {
        Set<Role> roles = roleService.findAllRolesByRoleTypes(Set.of(RoleType.ROLE_ADMIN));

        Assertions.assertThat(roles).hasSize(1)
                .extracting(Role::getRoleType)
                .contains(RoleType.ROLE_ADMIN);
    }
}
