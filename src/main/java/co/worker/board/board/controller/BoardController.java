package co.worker.board.board.controller;

import co.worker.board.board.model.BoardParam;
import co.worker.board.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(
            value = "/all",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(){
        return ResponseEntity.ok(boardService.getBoard());
    }

    @GetMapping(
            value = "/{seq}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity get(@PathVariable("seq") Long seq){
        return ResponseEntity.ok(boardService.getBoard(seq));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody @Valid BoardParam param){
        boardService.add(param);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity edit(@RequestBody @Valid BoardParam param,
                               @PathVariable("seq") Long seq){
        param.setSeq(seq);
        boardService.edit(param);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("seq") Long seq){
        boardService.delete(seq);
        return ResponseEntity.ok(null);
    }
}