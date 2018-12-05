package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.Ticket;
import kz.teamvictus.poll.core.model.TicketMessage;
import kz.teamvictus.poll.core.repository.TicketJpaRepo;
import kz.teamvictus.poll.core.repository.TicketMessageJpaRepo;
import kz.teamvictus.poll.core.service.ITicketMessageService;
import kz.teamvictus.poll.core.service.ITicketService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketMessageService implements ITicketMessageService {
   private static final Logger LOGGER = LoggerFactory.getLogger(TicketMessageService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private TicketMessageJpaRepo ticketMessageJpaRepo;

   @Override
   public TicketMessage getTicketMessageById(Long id) throws InternalException {
      try {
         return ticketMessageJpaRepo.getOne(id);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getTicketMessageById", e);
      }
   }

   @Override
   public List<TicketMessage> getAllTicketMessage() throws InternalException {
      try {
         return ticketMessageJpaRepo.findAll();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllTicketMessage", e);
      }
   }

   @Override
   public List<TicketMessage> getAllTicketMessageByTicketId(Long ticketId) throws InternalException {
      try {
         return ticketMessageJpaRepo.findAllByTicketId(ticketId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllTicketMessageByTicketId", e);
      }
   }

   @Override
   public TicketMessage updateTicketMessage(Long id, TicketMessage ticketMessage) throws InternalException {
      try {
         TicketMessage currentTicketMessage = ticketMessageJpaRepo.getOne(id);
         currentTicketMessage.setId(ticketMessage.getId());
         currentTicketMessage.setSenderId(ticketMessage.getSenderId());
         currentTicketMessage.setCreatedAt(ticketMessage.getCreatedAt());
         currentTicketMessage.setText(ticketMessage.getText());
         currentTicketMessage.setTicketId(ticketMessage.getTicketId());
         return ticketMessageJpaRepo.saveAndFlush(currentTicketMessage);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateTicketMessage", e);
      }
   }

   @Override
   public TicketMessage addTicketMessage(TicketMessage ticketMessage) throws InternalException {
      try {
         return ticketMessageJpaRepo.saveAndFlush(ticketMessage);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addTicketMessage", e);
      }
   }
}
