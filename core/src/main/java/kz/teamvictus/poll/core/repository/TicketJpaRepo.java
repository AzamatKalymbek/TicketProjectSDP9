package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.Role;
import kz.teamvictus.poll.core.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketJpaRepo extends JpaRepository<Ticket, Long> {
   Ticket findByUserIdAndId(Long userId, Long id);
   List<Ticket> findAllByUserId(Long userId);
   List<Ticket> findAllByTicketStatusId(Long statusId);

}
