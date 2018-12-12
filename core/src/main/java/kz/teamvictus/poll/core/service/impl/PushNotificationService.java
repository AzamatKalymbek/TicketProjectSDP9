package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.controller.PushNotificationOptions;
import kz.teamvictus.poll.core.model.PushNotification;
import kz.teamvictus.poll.core.model.Role;
import kz.teamvictus.poll.core.model.Ticket;
import kz.teamvictus.poll.core.repository.PushNotificationJpaRepo;
import kz.teamvictus.poll.core.repository.RoleJpaRepo;
import kz.teamvictus.poll.core.service.IPushNotificationService;
import kz.teamvictus.poll.core.service.IRoleService;
import kz.teamvictus.poll.core.service.IUserTokenService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushNotificationService implements IPushNotificationService {
   private static final Logger LOGGER = LoggerFactory.getLogger(PushNotificationService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private PushNotificationJpaRepo pushNotificationJpaRepo;
   @Autowired
   IUserTokenService iUserTokenService;

   @Override
   public PushNotification addPlayer(String userToken, String playerId) throws InternalException {
      try {
         Long userId = iUserTokenService.getUserIdFromToken(userToken);

         PushNotification pushNotification = pushNotificationJpaRepo
                 .findPushNotificationByPlayerIdAndUserId(playerId, userId);
         if (pushNotification == null) {
            pushNotification = new PushNotification();
         }

         pushNotification.setUserId(userId);
         pushNotification.setPlayerId(playerId);
         return pushNotificationJpaRepo.saveAndFlush(pushNotification);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addPlayer", e);
      }
   }

   @Override
   public String deletePlayer(String userToken, String playerId) throws InternalException {
      try {
         Long userId = iUserTokenService.getUserIdFromToken(userToken);

         PushNotification pushNotification = pushNotificationJpaRepo
                 .findPushNotificationByPlayerIdAndUserId(playerId, userId);
         if (pushNotification != null) {
            pushNotificationJpaRepo.delete(pushNotification.getId());
         }

         return "Successfully was deleted";
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:deletePlayer", e);
      }
   }

   @Override
   public void sendMessageToUser(Long userId) throws InternalException {
      List<PushNotification> pushNotifications = pushNotificationJpaRepo.findPushNotificationsByUserId(userId);
      for (PushNotification pushNotification: pushNotifications) {
         PushNotificationOptions.sendMessageToUser("You have new message!", pushNotification.getPlayerId());
      }
   }

   @Override
   public void sendInfoToUser(Long userId, String message) throws InternalException {
      List<PushNotification> pushNotifications = pushNotificationJpaRepo.findPushNotificationsByUserId(userId);
      for (PushNotification pushNotification: pushNotifications) {
         PushNotificationOptions.sendMessageToUser(message, pushNotification.getPlayerId());
      }
   }

   @Override
   public void sendTicketMessageToUser(Ticket ticket, Long userId) throws InternalException {
      List<PushNotification> pushNotifications = pushNotificationJpaRepo.findPushNotificationsByUserId(userId);
      for (PushNotification pushNotification: pushNotifications) {
         PushNotificationOptions.sendTicketMessageToUser(
                 "New message for: " + ticket.getTitle(),
                 ticket.getId(),
                 pushNotification.getPlayerId()
         );
      }
   }
}
