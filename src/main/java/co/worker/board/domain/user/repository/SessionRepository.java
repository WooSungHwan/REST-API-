package co.worker.board.domain.user.repository;

import co.worker.board.domain.user.model.SessionData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionData, String> {
}
