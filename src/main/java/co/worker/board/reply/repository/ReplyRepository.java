package co.worker.board.reply.repository;

import co.worker.board.reply.model.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    Page<ReplyEntity> findByBoardSeq(Long boardSeq, Pageable pageable);
}
