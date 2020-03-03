package co.worker.board.favorite.service;

import co.worker.board.favorite.model.FavoriteEntity;
import co.worker.board.favorite.model.FavoriteParam;
import co.worker.board.favorite.repository.FavoriteRepository;
import co.worker.board.util.TypeChange;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository){
        this.favoriteRepository = favoriteRepository;
    }

    /* 특정유저의 즐겨찾기 등록 */
    public Object add(FavoriteParam param) throws Exception {
        return favoriteRepository.save(TypeChange.sourceToDestination(param, new FavoriteEntity()));
    }

    /* 특정 게시물의 즐겨찾기 유저수. */
    public Integer getFavoriteBoardCount(Long boardSeq) {
        return favoriteRepository.getFavoriteBoardCount(boardSeq);
    }

    /* 특정 게시물의 즐겨찾기 유저목록. */

    /* 특정 유저의 즐겨찾기 게시물. */
    public Object getFavoriteUsersBoards(Long userSeq) {
        return favoriteRepository.getFavoriteUsersBoards(userSeq);
    }

    /* 조회용 모든 테이블 출력 */
    public Object getAll() throws Exception {
        return favoriteRepository.findAll();
    }
}
