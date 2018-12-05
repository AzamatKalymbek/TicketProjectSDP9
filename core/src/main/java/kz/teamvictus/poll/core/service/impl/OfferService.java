package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.Offer;
import kz.teamvictus.poll.core.repository.OfferJpaRepo;
import kz.teamvictus.poll.core.service.IOfferService;
import kz.teamvictus.poll.core.service.IUserTokenService;
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
   IUserTokenService iUserTokenService;
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
   public List<Offer> getByTicketId(Long ticketId) throws InternalException {
      try {
         return offerJpaRepo.findByTicketId( ticketId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getByTicketId", e);
      }
   }

   @Override
   public Offer getByUserIdAndTicketId(String userToken, Long ticketId) throws InternalException {
      try {
         Long userId = iUserTokenService.getUserIdFromToken(userToken);
         return offerJpaRepo.findByUserIdAndTicketId(userId, ticketId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllByUserIdAndTicketId", e);
      }
   }

   @Override
   public List<Offer> getAllByUserIdAndStatusId(String userToken, Long statusId) throws InternalException {
      try {
         Long userId = iUserTokenService.getUserIdFromToken(userToken);
         return offerJpaRepo.findAllByUserIdAndOfferStatusId(userId, statusId);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllByUserIdAndStatusId", e);
      }
   }

   @Override
   public Offer updateOffer(Long id, Offer offer) throws InternalException {
      try {
         Offer currentOffer = offerJpaRepo.getOne(id);
         currentOffer.setId(offer.getId());
         currentOffer.setDuration(offer.getDuration());
         currentOffer.setTicketId(offer.getTicketId());
         currentOffer.setPrice(offer.getPrice());
         currentOffer.setUserId(offer.getUserId());
         currentOffer.setOfferStatusId(offer.getOfferStatusId());
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
