package co.worker.board.domain.reply.repository;

import co.worker.board.domain.reply.model.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    List<ReplyEntity> findByBoardSeq(Long boardSeq);

    void deleteByUserSeq(Long seq);
}
