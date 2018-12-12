package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.TicketStatus;
import kz.teamvictus.poll.core.repository.TicketStatusJpaRepo;
import kz.teamvictus.poll.core.service.ITicketStatusService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusService implements ITicketStatusService {
   private static final Logger LOGGER = LoggerFactory.getLogger(TicketStatusService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private TicketStatusJpaRepo ticketStatusJpaRepo;
   
   @Override
   public TicketStatus getTicketStatusById(Long id) throws InternalException {
      try {
         return ticketStatusJpaRepo.getOne(id);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getTicketStatusById", e);
      }
   }

   @Override
   public TicketStatus getTicketStatusByCode(String code) throws InternalException {
      try {
         return ticketStatusJpaRepo.findTicketStatusByCode(code);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getTicketStatusByCode", e);
      }
   }

   @Override
   public List<TicketStatus> getAllTicketStatus() throws InternalException {
      try {
         return ticketStatusJpaRepo.findAll();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllTicketStatus", e);
      }
   }

   @Override
   public TicketStatus updateTicketStatus(Long id, TicketStatus ticketStatus) throws InternalException {
      try {
         TicketStatus currentTicket = ticketStatusJpaRepo.getOne(id);
         currentTicket.setId(ticketStatus.getId());
         currentTicket.setCode(ticketStatus.getCode());
         currentTicket.setName(ticketStatus.getName());
         return ticketStatusJpaRepo.saveAndFlush(currentTicket);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateTicketStatus", e);
      }
   }

   @Override
   public TicketStatus addTicketStatus(TicketStatus ticketStatus) throws InternalException {
      try {
         return ticketStatusJpaRepo.saveAndFlush(ticketStatus);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addTicketStatus", e);
      }
   }
}
