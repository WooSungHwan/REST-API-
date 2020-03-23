package co.worker.board.configuration.security;

import co.worker.board.domain.user.model.SecurityUser;
import co.worker.board.domain.user.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

public class MyLogoutHandler implements LogoutHandler {

    @Autowired
    private SessionService sessionService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println("##logout Handler");

        SecurityUser user = (SecurityUser)authentication.getPrincipal();

        Cookie[] cookies = request.getCookies();

        Cookie sessionCookie = Arrays.stream(cookies).filter(cookie -> Objects.equals(cookie.getName(), "auth_key")).findFirst().get();
        sessionService.deleteSession(sessionCookie.getValue());
    }
}
