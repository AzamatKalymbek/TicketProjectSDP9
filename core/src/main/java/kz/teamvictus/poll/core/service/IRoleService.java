package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.Role;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface IRoleService {
   Role getRoleById(Long id) throws InternalException;
   Role getRoleByCode(String code) throws InternalException;
   List<Role> getAllRole() throws InternalException;
   Role updateRole(Long id, Role role) throws InternalException;
   Role addRole(Role role) throws InternalException;
}
