package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.OfferUser;
import kz.teamvictus.poll.core.repository.OfferUserJpaRepo;
import kz.teamvictus.poll.core.service.IOfferUserService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferUserService implements IOfferUserService {
   private static final Logger LOGGER = LoggerFactory.getLogger(OfferUserService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private OfferUserJpaRepo offerUserJpaRepo;

   @Override
   public OfferUser getOfferUserById(Long id) throws InternalException {
      try {
         return offerUserJpaRepo.getOne(id);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getOfferUserById", e);
      }
   }

   @Override
   public List<OfferUser> getAllOfferUser() throws InternalException {
      try {
         return offerUserJpaRepo.findAll();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllOfferUser", e);
      }
   }

   @Override
   public OfferUser updateOfferUser(Long id, OfferUser offerUser) throws InternalException {
      try {
         OfferUser currentOfferUser = offerUserJpaRepo.getOne(id);
         currentOfferUser.setId(offerUser.getId());
         currentOfferUser.setDuration(offerUser.getDuration());
         currentOfferUser.setOfferId(offerUser.getOfferId());
         currentOfferUser.setPrice(offerUser.getPrice());
         currentOfferUser.setUserId(offerUser.getUserId());
         return offerUserJpaRepo.saveAndFlush(currentOfferUser);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateOfferUser", e);
      }
   }

   @Override
   public OfferUser addOfferUser(OfferUser offerUser) throws InternalException {
      try {
         return offerUserJpaRepo.saveAndFlush(offerUser);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addOfferUser", e);
      }
   }
}
