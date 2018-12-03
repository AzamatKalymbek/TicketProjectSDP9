package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.OfferStatus;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface IOfferStatusService {
   OfferStatus getOfferStatusById(Long id) throws InternalException;
   List<OfferStatus> getAllOfferStatus() throws InternalException;
   OfferStatus updateOfferStatus(Long id, OfferStatus offerStatus) throws InternalException;
   OfferStatus addOfferStatus(OfferStatus offerStatus) throws InternalException;
}
