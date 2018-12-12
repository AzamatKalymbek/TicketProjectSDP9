package kz.teamvictus.poll.core.repository.join;

import kz.teamvictus.poll.core.model.Ticket;
import kz.teamvictus.poll.core.model.User;
import kz.teamvictus.poll.core.model.join.JoinTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinTicketJpaRepo extends JpaRepository<JoinTicket, Long> {
   List<JoinTicket> findAllByUser(User user);
}
