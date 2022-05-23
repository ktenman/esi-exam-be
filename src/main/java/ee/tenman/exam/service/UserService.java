package ee.tenman.exam.service;

import ee.tenman.exam.exception.UserNotFoundException;
import ee.tenman.exam.domain.User;
import ee.tenman.exam.repository.UserRepository;
import ee.tenman.exam.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserWithRoles() {
        return SecurityUtils.getCurrentUsername().flatMap(userRepository::findTopByUsername)
                .orElseThrow(UserNotFoundException::new);
    }
}
