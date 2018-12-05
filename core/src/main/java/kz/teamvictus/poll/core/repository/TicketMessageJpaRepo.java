package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketMessageJpaRepo extends JpaRepository<TicketMessage, Long> {

   List<TicketMessage> findAllByTicketId(Long ticketId);
}
