package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.OfferStatus;
import kz.teamvictus.poll.core.repository.OfferStatusJpaRepo;
import kz.teamvictus.poll.core.service.IOfferStatusService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferStatusService implements IOfferStatusService {
   private static final Logger LOGGER = LoggerFactory.getLogger(OfferStatusService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private OfferStatusJpaRepo offerStatusJpaRepo;

   @Override
   public OfferStatus getOfferStatusById(Long id) throws InternalException {
      try {
         return offerStatusJpaRepo.getOne(id);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getOfferStatusById", e);
      }
   }

   @Override
   public List<OfferStatus> getAllOfferStatus() throws InternalException {
      try {
         return offerStatusJpaRepo.findAll();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllOfferStatus", e);
      }
   }

   @Override
   public OfferStatus updateOfferStatus(Long id, OfferStatus offerStatus) throws InternalException {
      try {
         OfferStatus currentOfferStatus = offerStatusJpaRepo.getOne(id);
         currentOfferStatus.setId(offerStatus.getId());
         currentOfferStatus.setCode(offerStatus.getCode());
         currentOfferStatus.setName(offerStatus.getName());
         return offerStatusJpaRepo.saveAndFlush(currentOfferStatus);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateOfferStatus", e);
      }
   }

   @Override
   public OfferStatus addOfferStatus(OfferStatus offerStatus) throws InternalException {
      try {
         return offerStatusJpaRepo.saveAndFlush(offerStatus);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addOfferStatus", e);
      }
   }
}
