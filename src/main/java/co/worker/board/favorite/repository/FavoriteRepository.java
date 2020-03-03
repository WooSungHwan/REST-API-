package co.worker.board.favorite.repository;

import co.worker.board.board.model.BoardEntity;
import co.worker.board.favorite.model.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    @Query("SELECT COUNT(*) FROM FavoriteEntity WHERE boardSeq = :boardSeq")
    Integer getFavoriteBoardCount(@Param("boardSeq") Long boardSeq);

    @Query("SELECT 1 FROM BoardEntity") // * FROM BoardEntity WHERE seq IN (SELECT board)")
    List<BoardEntity> getFavoriteUsersBoards(Long userSeq);
}
