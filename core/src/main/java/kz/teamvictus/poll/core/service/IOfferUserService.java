package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.OfferUser;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface IOfferUserService {
   OfferUser getOfferUserById(Long id) throws InternalException;
   List<OfferUser> getAllOfferUser() throws InternalException;
   OfferUser updateOfferUser(Long id, OfferUser offerUser) throws InternalException;
   OfferUser addOfferUser(OfferUser offerUser) throws InternalException;
}
