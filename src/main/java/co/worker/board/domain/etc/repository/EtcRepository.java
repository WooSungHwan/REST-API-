package co.worker.board.domain.etc.repository;

import co.worker.board.domain.etc.model.FileVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtcRepository extends JpaRepository<FileVO, Long> {
}
