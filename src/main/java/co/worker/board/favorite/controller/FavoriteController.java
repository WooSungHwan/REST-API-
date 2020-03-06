package co.worker.board.favorite.controller;

import co.worker.board.favorite.model.FavoriteParam;
import co.worker.board.favorite.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    private FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody @Valid FavoriteParam param) {
        return favoriteService.add(param);
    }

    @GetMapping("/all")
    public Object getAll() throws Exception {
        return favoriteService.getAll();
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@RequestBody @Valid FavoriteParam param) {
        return favoriteService.delete(param);
    }

    /* 특정 게시물의 즐겨찾기 유저수. */
    @GetMapping(value = "/favorite/boards/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getFavoriteBoardCount(@PathVariable("seq") Long boardSeq) {
        return favoriteService.getFavoriteBoardCount(boardSeq);
    }

    /* 특정 유저의 즐겨찾기 게시물. */
    @GetMapping(value = "/favorite/users/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getFavoriteUsersBoards(@PathVariable("seq") Long userSeq) {
        return favoriteService.getFavoriteUsersBoards(userSeq);
    }

    /* 특정 게시물의 즐겨찾기 유저목록. */
    @GetMapping(value = "/get/boarduser/{boardSeq}")
    public Object getFavoritesFromBoardSeq(@PathVariable("boardSeq") @Min(1) Long boardSeq){
        return favoriteService.getFavoritesFromBoardSeq(boardSeq);
    }
}
