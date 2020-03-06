package co.worker.board.favorite.service;

import co.worker.board.favorite.model.FavoriteEntity;
import co.worker.board.favorite.model.FavoriteParam;
import co.worker.board.favorite.repository.FavoriteRepository;
import co.worker.board.user.model.UserEntity;
import co.worker.board.user.model.UserResult;
import co.worker.board.user.repository.UserRepository;
import co.worker.board.util.TypeChange;
import co.worker.board.util.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private FavoriteRepository favoriteRepository;
    private UserRepository userRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository){
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
    }

    /* 특정유저의 즐겨찾기 등록 */
    public Object add(FavoriteParam param) {
        return favoriteRepository.save(TypeChange.sourceToDestination(param, new FavoriteEntity()));
    }

    /* 특정 게시물의 즐겨찾기 유저수. */
    public Integer getFavoriteBoardCount(Long boardSeq) {
        return favoriteRepository.getFavoriteBoardCount(boardSeq);
    }

    /* 특정 유저의 즐겨찾기 게시물. */
    public Object getFavoriteUsersBoards(Long userSeq) {
        return favoriteRepository.getFavoriteUsersBoards(userSeq);
    }

    /* 조회용 모든 테이블 출력 */
    public Object getAll() throws Exception {
        return favoriteRepository.findAll();
    }

    public Object delete(FavoriteParam param){
        Optional<FavoriteEntity> favoriteEntity = favoriteRepository.getFavoriteEntityByBoardSeqAndUserSeq(param.getBoardSeq(), param.getUserSeq());
        favoriteEntity.ifPresent(favoriteEntity1 -> favoriteRepository.delete(favoriteEntity1));
        return null;
    }

    /* 특정 게시물의 즐겨찾기 유저목록. */
    public Object getFavoritesFromBoardSeq(Long boardSeq) {
        //즐겨찾기 목록 가져오기
        List<FavoriteEntity> favoriteEntityList = favoriteRepository.getFavoriteEntitiesByBoardSeq(boardSeq);
        List<UserResult> results = new ArrayList<>();
        favoriteEntityList.stream().forEach(favoriteEntity -> {
            //유저 가져오기
            Optional<UserEntity> user = userRepository.findById(favoriteEntity.getUserSeq());
            user.ifPresent(userEntity -> results.add(TypeChange.sourceToDestination(userEntity, new UserResult())));
        });
        return results;
    }

    public void deleteByUserSeq(Long userSeq){
        favoriteRepository.deleteByUserSeq(userSeq);
    }
}
