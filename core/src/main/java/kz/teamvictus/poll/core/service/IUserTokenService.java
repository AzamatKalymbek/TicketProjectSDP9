package kz.teamvictus.poll.core.service;

import kz.teamvictus.utils.error.InternalException;

public interface IUserTokenService {
   Long getUserIdFromToken(String token) throws InternalException;
}
