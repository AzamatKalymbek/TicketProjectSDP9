package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.Offer;
import kz.teamvictus.poll.core.model.Ticket;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface ITicketService {


   Ticket getTicketById(Long id) throws InternalException;
   List<Ticket> getAllTicket() throws InternalException;
   List<Ticket> getAllTicketByUserId(String userToken) throws InternalException;
   List<Ticket> getAllTicketByTicketStatusId(Long statusId) throws InternalException;
   Ticket getTicketByIdAndUserId(String userToken, Long id) throws InternalException;
   Ticket updateTicket(Long id, Ticket ticket) throws InternalException;
   Ticket addTicket(String userToken, Ticket ticket) throws InternalException;
}
