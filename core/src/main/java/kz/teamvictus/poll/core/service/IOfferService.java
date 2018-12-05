package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.Offer;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface IOfferService {
   Offer getOfferById(Long id) throws InternalException;
   Offer getByUserIdAndTicketId(String userToken, Long ticketId) throws InternalException;
   List<Offer> getAllOffer() throws InternalException;
   List<Offer> getByTicketId(Long ticketId) throws InternalException;
   List<Offer> getAllByUserIdAndStatusId(String userToken, Long statusId) throws InternalException;

   Offer updateOffer(Long id, Offer offer) throws InternalException;
   Offer addOffer(Offer offer) throws InternalException;
}
