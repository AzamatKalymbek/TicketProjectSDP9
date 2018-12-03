package kz.teamvictus.poll.security.service;

import kz.teamvictus.poll.security.model.CleanSecurityDBSession;
import kz.teamvictus.poll.security.model.CleanSecurityDBSessionState;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.model.UserSessionInfo;

import java.util.HashMap;
import java.util.List;

public interface ISecurityService {
    HashMap<String, Object> getSessionInfo(String token) throws InternalException;
    String getSessionUsername() throws InternalException;
}
