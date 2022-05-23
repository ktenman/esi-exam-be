package ee.tenman.exam.service;

import ee.tenman.exam.domain.RoleType;
import ee.tenman.exam.domain.Role;
import ee.tenman.exam.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AddInitialDataService {

    @Resource
    private RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct() {
        List<RoleType> storedRoles = roleRepository.findAll().stream()
                .map(Role::getRoleType)
                .toList();
        Stream.of(RoleType.values())
                .filter(r -> !storedRoles.contains(r))
                .map(Role::new)
                .forEach(roleRepository::save);
    }

}
