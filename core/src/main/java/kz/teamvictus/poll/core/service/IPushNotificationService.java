package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.PushNotification;
import kz.teamvictus.poll.core.model.Role;
import kz.teamvictus.poll.core.model.Ticket;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface IPushNotificationService {
   PushNotification addPlayer(String userToken, String playerId) throws InternalException;
   String deletePlayer(String userToken, String playerId) throws InternalException;
   void sendMessageToUser(Long userId) throws InternalException;
   void sendInfoToUser(Long userId, String message) throws InternalException;
   void sendTicketMessageToUser(Ticket ticket, Long userId) throws InternalException;
}
