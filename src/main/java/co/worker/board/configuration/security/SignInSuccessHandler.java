package co.worker.board.configuration.security;

import co.worker.board.domain.user.model.SecurityUser;
import co.worker.board.domain.user.model.UserEntity;
import co.worker.board.domain.user.repository.UserRepository;
import co.worker.board.domain.user.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SignInSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("## 인증성공.");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        SecurityUser loginUser = (SecurityUser)authentication.getPrincipal();
        String sessionId = sessionService.createSession(loginUser);
        httpServletResponse.addCookie(new Cookie("auth_key", sessionId));

        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}
