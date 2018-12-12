package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.Ticket;
import kz.teamvictus.poll.core.model.TicketStatus;
import kz.teamvictus.poll.core.model.User;
import kz.teamvictus.poll.core.repository.TicketJpaRepo;
import kz.teamvictus.poll.core.service.*;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService implements ITicketService {
   private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private IUserTokenService iUserTokenService;
   @Autowired
   private TicketJpaRepo ticketJpaRepo;
   @Autowired
   private IUserService userService;
   @Autowired
   private IPushNotificationService pushNotificationService;

   @Override
   public Ticket getTicketById(Long id) throws InternalException {
      try {
         return ticketJpaRepo.findOne(id);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getTicketById", e);
      }
   }

   @Override
   public List<Ticket> getAllTicket() throws InternalException {
      try {
         return ticketJpaRepo.findAll();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllTicket", e);
      }
   }

   @Override
   public List<Ticket> getAllTicketByUserId(String userToken) throws InternalException {
      try {
         Long userId = iUserTokenService.getUserIdFromToken(userToken);

         return ticketJpaRepo.findAllByUserId(userId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllTicketByUserId", e);
      }
   }

   @Override
   public List<Ticket> getAllTicketByTicketStatusId(Long statusId) throws InternalException {
      try {
         return ticketJpaRepo.findAllByTicketStatusId(statusId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllTicketByUserIdAndTicketStatusId", e);
      }
   }

   @Override
   public Ticket getTicketByIdAndUserId(String userToken, Long ticketId) throws InternalException {
      try {
         Long userId = iUserTokenService.getUserIdFromToken(userToken);

         return ticketJpaRepo.findByUserIdAndId(userId, ticketId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getTicketByIdAndUserId", e);
      }
   }

   @Override
   public Ticket updateTicket(Long id, Ticket ticket) throws InternalException {
      try {
         Ticket currentTicket = ticketJpaRepo.getOne(id);
         currentTicket.setId(ticket.getId());
         currentTicket.setCategoryId(ticket.getCategoryId());
         currentTicket.setUserId(ticket.getUserId());
         currentTicket.setCreatedAt(ticket.getCreatedAt());
         currentTicket.setText(ticket.getText());
         currentTicket.setTicketStatusId(ticket.getTicketStatusId());
         currentTicket.setTitle(ticket.getTitle());
         return ticketJpaRepo.saveAndFlush(currentTicket);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateTicket", e);
      }
   }

   @Override
   public Ticket addTicket(String userToken, Ticket ticket) throws InternalException {
      try {
         List<User> users = userService.getExperts();
         for (User user: users) {
            pushNotificationService.sendInfoToUser(user.getId(), "New ticket for experts!");
         }
         return ticketJpaRepo.saveAndFlush(ticket);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addTicket", e);
      }
   }


}
