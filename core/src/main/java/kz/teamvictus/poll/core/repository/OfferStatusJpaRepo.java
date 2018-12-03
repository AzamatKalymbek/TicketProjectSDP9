package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferStatusJpaRepo extends JpaRepository<OfferStatus, Long> {
}
