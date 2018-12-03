package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Long> {

   User findByUsername(String username);

}
