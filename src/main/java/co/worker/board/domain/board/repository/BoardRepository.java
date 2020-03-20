package co.worker.board.domain.board.repository;

import co.worker.board.domain.board.model.BoardEntity;
import co.worker.board.domain.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findAll(Pageable pageable);

    Page<BoardEntity> findByUserEntity(UserEntity userEntity, Pageable pageable);

    List<BoardEntity> findByUserEntity(UserEntity userEntity);

    @Query("DELETE FROM BoardEntity WHERE userEntity = :user")
    void deleteByUserSeq(@Param("user") UserEntity user);
}
