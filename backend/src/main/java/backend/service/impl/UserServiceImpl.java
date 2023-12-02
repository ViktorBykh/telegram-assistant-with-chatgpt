package backend.service.impl;

import backend.model.User;
import backend.repository.UserRepository;
import backend.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByExternalId(Long externalId) {
        return userRepository.findByExternalId(externalId);
    }

    @Override
    public List<User> getAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }
}
