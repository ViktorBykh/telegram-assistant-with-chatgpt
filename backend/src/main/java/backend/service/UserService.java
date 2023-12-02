package backend.service;

import backend.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;

public interface UserService {
    User create(User user);

    Optional<User> getByExternalId(Long externalId);

    List<User> getAll(PageRequest pageRequest);
}
