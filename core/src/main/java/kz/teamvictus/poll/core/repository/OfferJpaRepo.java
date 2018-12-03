package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferJpaRepo extends JpaRepository<Offer, Long> {
}
