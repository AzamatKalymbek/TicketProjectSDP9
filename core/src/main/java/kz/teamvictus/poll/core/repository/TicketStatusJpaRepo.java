package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStatusJpaRepo extends JpaRepository<TicketStatus, Long> {
}
