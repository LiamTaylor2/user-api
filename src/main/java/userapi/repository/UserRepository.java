package userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userapi.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
