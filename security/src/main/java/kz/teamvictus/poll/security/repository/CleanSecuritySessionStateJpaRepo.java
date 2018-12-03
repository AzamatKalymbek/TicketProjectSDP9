package kz.teamvictus.poll.security.repository;

import kz.teamvictus.poll.security.model.CleanSecurityDBSessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CleanSecuritySessionStateJpaRepo extends JpaRepository<CleanSecurityDBSessionState, Long> {
    CleanSecurityDBSessionState findCleanCoreDBSessionStateByName(String name);
}
