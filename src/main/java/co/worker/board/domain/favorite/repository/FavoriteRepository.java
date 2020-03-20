package co.worker.board.domain.favorite.repository;

import co.worker.board.domain.board.model.BoardEntity;
import co.worker.board.domain.favorite.model.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    @Query("SELECT COUNT(*) FROM FavoriteEntity WHERE boardSeq = :boardSeq")
    Integer getFavoriteBoardCount(@Param("boardSeq") Long boardSeq);

    @Query("SELECT 1 FROM BoardEntity") // * FROM BoardEntity WHERE seq IN (SELECT board)")
    List<BoardEntity> getFavoriteUsersBoards(Long userSeq);

    Optional<FavoriteEntity> getFavoriteEntityByBoardSeqAndUserSeq(Long BoardSeq, Long UserSeq);

    List<FavoriteEntity> getFavoriteEntitiesByBoardSeq(Long boardSeq);

    void deleteByUserSeq(Long userSeq);
}
