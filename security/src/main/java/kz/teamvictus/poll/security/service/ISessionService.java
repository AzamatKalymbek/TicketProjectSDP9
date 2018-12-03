package kz.teamvictus.poll.security.service;

import kz.teamvictus.poll.security.model.CleanSecurityDBSession;
import kz.teamvictus.poll.security.model.CleanSecurityDBSessionState;
import kz.teamvictus.utils.error.InternalException;

import java.util.List;

public interface ISessionService {

    List<CleanSecurityDBSession> getAllSession() throws InternalException;
    List<CleanSecurityDBSession> getAllSessionByUsername(String username) throws InternalException;
    CleanSecurityDBSession getSessionBySessionToken(String token) throws InternalException;
    CleanSecurityDBSession getSessionBySessionId(Long id) throws InternalException;
    CleanSecurityDBSessionState getSessionStateByName(String name) throws InternalException;

    CleanSecurityDBSession createSession(CleanSecurityDBSession session) throws InternalException;
    CleanSecurityDBSession setSessionClosed(String token) throws InternalException;
    CleanSecurityDBSession setSessionUpdateDate(String token) throws InternalException;
    CleanSecurityDBSession updateSession(Long id, CleanSecurityDBSession sessionDetail) throws InternalException;
}
