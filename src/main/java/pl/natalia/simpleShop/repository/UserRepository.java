package pl.natalia.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.natalia.simpleShop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(long userId);
    User findByLogin(String login);

}
