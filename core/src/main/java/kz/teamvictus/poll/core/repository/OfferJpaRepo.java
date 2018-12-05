package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferJpaRepo extends JpaRepository<Offer, Long> {

   List<Offer> findAllByUserIdAndTicketId(Long userId, Long ticketId);
}
