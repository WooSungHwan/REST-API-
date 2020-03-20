package co.worker.board.domain.reply.controller;

import co.worker.board.domain.reply.model.ReplyParam;
import co.worker.board.domain.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/replies")
@Validated
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody @Valid ReplyParam replyParam) throws Exception {
        return replyService.add(replyParam);
    }

    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object edit(@RequestBody @Valid ReplyParam replyParam) throws Exception {
        return replyService.edit(replyParam);
    }

    @DeleteMapping(value = "/{seq}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object delete(@PathVariable("seq") @Min(1) Long seq) throws Exception {
        replyService.delete(seq);
        return null;
    }

    @GetMapping("/board/{seq}/{page}/{size}")
    public Object getRepliesByBoardSeq(@Min(1) @PathVariable("seq") Long boardSeq,
                                       @Min(0) @PathVariable("page") Integer page,
                                       @Min(5) @PathVariable("size") Integer size) throws Exception {
        return replyService.getRepliesByBoardSeq(boardSeq);
    }
}
