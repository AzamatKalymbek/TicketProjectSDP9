package kz.teamvictus.poll.core.service.impl;

import kz.teamvictus.poll.core.model.OfferUser;
import kz.teamvictus.poll.core.model.Role;
import kz.teamvictus.poll.core.repository.OfferUserJpaRepo;
import kz.teamvictus.poll.core.repository.RoleJpaRepo;
import kz.teamvictus.poll.core.service.IRoleService;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
   private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private RoleJpaRepo roleJpaRepo;
   
   @Override
   public Role getRoleById(Long id) throws InternalException {
      try {
         return roleJpaRepo.getOne(id);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getRoleById", e);
      }
   }

   @Override
   public List<Role> getAllRole() throws InternalException {
      try {
         return roleJpaRepo.findAll();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllRole", e);
      }
   }

   @Override
   public Role updateRole(Long id, Role role) throws InternalException {
      try {
         Role currentRole = roleJpaRepo.getOne(id);
         currentRole.setId(role.getId());
         currentRole.setCode(role.getCode());
         currentRole.setName(role.getName());
         return roleJpaRepo.saveAndFlush(currentRole);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateRole", e);
      }
   }

   @Override
   public Role addRole(Role role) throws InternalException {
      try {
         return roleJpaRepo.saveAndFlush(role);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:addRole", e);
      }
   }
}
