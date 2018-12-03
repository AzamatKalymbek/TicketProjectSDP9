package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.TicketStatus;
import kz.teamvictus.poll.core.model.User;
import kz.teamvictus.poll.core.repository.TicketStatusJpaRepo;
import kz.teamvictus.poll.core.repository.UserJpaRepo;
import kz.teamvictus.poll.core.service.IUserService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
   private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private UserJpaRepo userJpaRepo;

   @Override
   public User getUserById(Long id) throws InternalException {
      try {
         return userJpaRepo.getOne(id);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getUserById", e);
      }
   }

   @Override
   public User getUserByUsername(String username) throws InternalException {
      try {
         return userJpaRepo.findByUsername(username);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getUserByUsername", e);
      }
   }

   @Override
   public List<User> getAllUser() throws InternalException {
      try {
         return userJpaRepo.findAll();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllUser", e);
      }
   }

   @Override
   public User updateUser(Long id, User user) throws InternalException {
      try {
         User currentUser = userJpaRepo.getOne(id);
         currentUser.setId(user.getId());
         currentUser.setCategoryId(user.getCategoryId());
         currentUser.setEmail(user.getEmail());
         currentUser.setIsDisabled(user.getIsDisabled());
         currentUser.setIsVerified(user.getIsVerified());
         currentUser.setName(user.getName());
         currentUser.setPassword(user.getPassword());
         currentUser.setRoleId(user.getRoleId());
         currentUser.setSurname(user.getSurname());
         return userJpaRepo.saveAndFlush(currentUser);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateUser", e);
      }
   }

   @Override
   public User addUser(User user) throws InternalException {
      try {
         return userJpaRepo.saveAndFlush(user);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addUser", e);
      }
   }
}
