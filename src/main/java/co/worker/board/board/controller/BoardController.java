package co.worker.board.board.controller;

import co.worker.board.board.model.BoardParam;
import co.worker.board.board.service.BoardService;
import co.worker.board.configuration.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@RestController
@Validated
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(
            value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object getAll() throws Exception{
        return boardService.getBoard();
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

    @DeleteMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@PathVariable("seq") @Min(1) Long seq) throws Exception{
        boardService.delete(seq);
        return null;
    }
}