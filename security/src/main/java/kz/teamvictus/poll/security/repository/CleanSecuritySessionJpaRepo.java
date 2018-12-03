package kz.teamvictus.poll.security.repository;

import kz.teamvictus.poll.security.model.CleanSecurityDBSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CleanSecuritySessionJpaRepo extends JpaRepository<CleanSecurityDBSession, Long> {
    CleanSecurityDBSession findCleanCoreDBSessionBySessionToken(String token);
    List<CleanSecurityDBSession> findCleanCoreDBSessionByUsername(String username);
}
