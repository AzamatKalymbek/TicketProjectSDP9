package kz.teamvictus.poll.security.service;

import kz.teamvictus.utils.error.InternalException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public interface IAuthService {
    String login(String username, String password, HttpServletResponse response) throws InternalException;
    String logout(String token) throws InternalException;
    HashMap<String, Object> session(String token) throws InternalException;
}
