package kz.teamvictus.poll.core.service;

import kz.teamvictus.poll.core.model.User;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface IUserService {
   User getUserById(Long id) throws InternalException;
   User getUserByUsername(String username) throws InternalException;
   List<User> getExperts() throws InternalException;
   List<User> getAllUser() throws InternalException;
   User updateUser(Long id, User user) throws InternalException;
   User addUser(User user) throws InternalException;
}
