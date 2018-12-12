package kz.teamvictus.poll.core.repository;

import kz.teamvictus.poll.core.model.PushNotification;
import kz.teamvictus.poll.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PushNotificationJpaRepo extends JpaRepository<PushNotification, Long> {
    PushNotification findPushNotificationByPlayerIdAndUserId(String playerId, Long userId);
    List<PushNotification> findPushNotificationsByUserId(Long userId);
}
