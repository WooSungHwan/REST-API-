package co.worker.board.board.controller;

import co.worker.board.board.model.BoardParam;
import co.worker.board.board.service.BoardService;
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
            value = "/list/{page}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object getList(@PathVariable("page") Integer page) throws Exception{
        return boardService.getBoard(page);
    }

    @GetMapping(
            value = "/{seq}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object get(@PathVariable("seq") @Min(1) Long seq) throws Exception{
        return boardService.getBoard(seq);
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