package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.TicketStatus;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface ITicketStatusService {
   TicketStatus getTicketStatusById(Long id) throws InternalException;
   List<TicketStatus> getAllTicketStatus() throws InternalException;
   TicketStatus updateTicketStatus(Long id, TicketStatus ticketStatus) throws InternalException;
   TicketStatus addTicketStatus(TicketStatus ticketStatus) throws InternalException;
}
