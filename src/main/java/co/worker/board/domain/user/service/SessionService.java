package co.worker.board.domain.user.service;

import co.worker.board.domain.user.model.SecurityUser;
import co.worker.board.domain.user.model.SessionData;
import co.worker.board.domain.user.model.UserEntity;
import co.worker.board.domain.user.model.UserParam;
import co.worker.board.domain.user.repository.SessionRepository;
import co.worker.board.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public String createSession(SecurityUser user) {
        SessionData sessionData = new SessionData(userRepository.findByUserId(user.getId()).get());
        sessionRepository.save(sessionData);
        return sessionData.getId();
    }

    public void deleteSession(String sessionId) {
        SessionData sessionData = sessionRepository.getOne(sessionId);
        sessionRepository.delete(sessionData);
    }
}
