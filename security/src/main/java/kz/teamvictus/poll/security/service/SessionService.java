package kz.teamvictus.poll.security.service;

import kz.teamvictus.poll.security.model.CleanSecurityDBSession;
import kz.teamvictus.poll.security.model.CleanSecurityDBSessionState;
import kz.teamvictus.poll.security.repository.CleanSecuritySessionJpaRepo;
import kz.teamvictus.poll.security.repository.CleanSecuritySessionStateJpaRepo;
import kz.teamvictus.utils.error.ErrorCode;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import kz.teamvictus.utils.error.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SessionService implements ISessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);
    private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

    @Autowired
    private CleanSecuritySessionJpaRepo cleanSecuritySessionJpaRepo;
    @Autowired
    private CleanSecuritySessionStateJpaRepo cleanSecuritySessionStateJpaRepo;

    @Override
    public List<CleanSecurityDBSession> getAllSession() throws InternalException {
        try {
            return cleanSecuritySessionJpaRepo.findAll();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:getAllSession", e);
        }
    }

    @Override
    public List<CleanSecurityDBSession> getAllSessionByUsername(String username) throws InternalException {
        try {
            return cleanSecuritySessionJpaRepo.findCleanCoreDBSessionByUsername(username);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR,
                    "Exception:getAllSessionByUsername " + username, e);
        }
    }

    @Override
    public CleanSecurityDBSession getSessionBySessionToken(String token) throws InternalException {
        try {
            return cleanSecuritySessionJpaRepo.findCleanCoreDBSessionBySessionToken(token);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR,
                    "Exception:getSessionBySessionToken " + token, e);
        }
    }

    @Override
    public CleanSecurityDBSession getSessionBySessionId(Long id) throws InternalException {
        try {
            return cleanSecuritySessionJpaRepo.findOne(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR,
                    "Exception:getSessionBySessionId " + id, e);
        }
    }

    @Override
    public CleanSecurityDBSessionState getSessionStateByName(String name) throws InternalException {
        try {
            return cleanSecuritySessionStateJpaRepo.findCleanCoreDBSessionStateByName(name);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR,
                    "Exception:getSessionStateByName " + name, e);
        }
    }

    @Override
    public CleanSecurityDBSession createSession(CleanSecurityDBSession session) throws InternalException {
        try {
            return cleanSecuritySessionJpaRepo.save(session);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:createSession", e,
                    new Pair("session", session));
        }
    }

    @Transactional
    @Override
    public CleanSecurityDBSession setSessionClosed(String token) throws InternalException {
        try {
            LOGGER.debug("Set session closed: " + token);
            CleanSecurityDBSession dbSession = cleanSecuritySessionJpaRepo.findCleanCoreDBSessionBySessionToken(token);
            dbSession.setStateId(cleanSecuritySessionStateJpaRepo.findCleanCoreDBSessionStateByName("closed").getStateId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(new java.util.Date());
            dbSession.setCloseDate(currentTime);
            return cleanSecuritySessionJpaRepo.save(dbSession);
        } catch (Exception e) {
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:setSessionClosed " + token, e);
        }
    }

    @Transactional
    @Override
    public CleanSecurityDBSession setSessionUpdateDate(String token) throws InternalException {
        try {
            CleanSecurityDBSession dbSession = cleanSecuritySessionJpaRepo.findCleanCoreDBSessionBySessionToken(token);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(new java.util.Date());
            dbSession.setUpdateDate(currentTime);
            return cleanSecuritySessionJpaRepo.save(dbSession);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:setSessionUpdateDate " + token, e);
        }
    }

    @Transactional
    @Override
    public CleanSecurityDBSession updateSession(Long id, CleanSecurityDBSession session) throws InternalException {
        try {
            CleanSecurityDBSession dbSession = cleanSecuritySessionJpaRepo.findOne(id);
            dbSession.setUsername(session.getUsername());
            dbSession.setCloseDate(session.getCloseDate());
            dbSession.setUpdateDate(session.getUpdateDate());
            dbSession.setStateId(session.getStateId());
            return cleanSecuritySessionJpaRepo.save(dbSession);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw IE_HELPER.generate(ErrorCode.ErrorCodes.SYSTEM_ERROR, "Exception:updateSession " + id, e);
        }
    }
}
