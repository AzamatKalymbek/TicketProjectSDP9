package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.TicketMessage;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface ITicketMessageService {
   TicketMessage getTicketMessageById(Long id) throws InternalException;
   List<TicketMessage> getAllTicketMessage() throws InternalException;
   TicketMessage updateTicketMessage(Long id, TicketMessage ticketMessage) throws InternalException;
   TicketMessage addTicketMessage(TicketMessage ticketMessage) throws InternalException;
}
