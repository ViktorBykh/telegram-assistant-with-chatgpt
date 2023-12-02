package backend.repository;

import backend.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByExternalId(Long externalId);

    @Query("SELECT user FROM User user")
    List<User> findAll(PageRequest pageRequest);
}
