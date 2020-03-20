package co.worker.board.domain.board.controller;

import co.worker.board.domain.board.model.BoardParam;
import co.worker.board.domain.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(
            value = "/list/{page}/{size}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object getList(@PathVariable("page") Integer page, @PathVariable("size") Integer size) throws Exception{
        return boardService.getBoard(page, size);
    }

    @GetMapping(
            value = "/{seq}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object get(@PathVariable("seq") @Min(1) Long seq) throws Exception{
        return boardService.getBoard(seq);
    }

    @GetMapping(
            value = "/user/{userSeq}/{page}/{size}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object getByUserSeq(@PathVariable("userSeq") @Min(1) Long userSeq,
                               @PathVariable("page") @Min(0) Integer page,
                               @PathVariable("size") @Min(5) Integer size) throws Exception{
        return boardService.getBoardByUserSeq(userSeq, page, size);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody @Valid BoardParam param) throws Exception{
        boardService.add(param);
        return null;
    }

    @PutMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object edit(@RequestBody @Valid BoardParam param,
                               @PathVariable("seq") @Min(1) Long seq) throws Exception{
        param.setSeq(seq);
        boardService.edit(param);
        return null;
    }

    @DeleteMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@PathVariable("seq") @Min(1) Long seq) throws Exception{
        boardService.delete(seq);
        return null;
    }
}