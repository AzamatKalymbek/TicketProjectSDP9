package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.Offer;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface IOfferService {
   Offer getOfferById(Long id) throws InternalException;
   List<Offer> getAllOffer() throws InternalException;
   Offer updateOffer(Long id, Offer offer) throws InternalException;
   Offer addOffer(Offer offer) throws InternalException;
}
