package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.OfferUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferUserJpaRepo extends JpaRepository<OfferUser, Long> {
}
