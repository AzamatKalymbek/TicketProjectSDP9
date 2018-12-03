package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.Offer;
import kz.teamvictus.poll.core.repository.OfferJpaRepo;
import kz.teamvictus.poll.core.service.IOfferService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService implements IOfferService {
   private static final Logger LOGGER = LoggerFactory.getLogger(OfferService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private OfferJpaRepo offerJpaRepo;

   @Override
   public Offer getOfferById(Long id) throws InternalException {
      try {
         return offerJpaRepo.getOne(id);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getOfferById", e);
      }
   }

   @Override
   public List<Offer> getAllOffer() throws InternalException {
      try {
         return offerJpaRepo.findAll();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllOffer", e);
      }
   }

   @Override
   public Offer updateOffer(Long id, Offer offer) throws InternalException {
      try {
         Offer currentOffer = offerJpaRepo.getOne(id);
         currentOffer.setId(offer.getId());
         currentOffer.setOfferStatusId(offer.getOfferStatusId());
         currentOffer.setTicketId(offer.getTicketId());
         currentOffer.setUserId(offer.getUserId());
         return offerJpaRepo.saveAndFlush(currentOffer);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateOffer", e);
      }
   }

   @Override
   public Offer addOffer(Offer offer) throws InternalException {
      try {
         return offerJpaRepo.saveAndFlush(offer);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addOffer", e);
      }
   }
}
