package co.worker.board.domain.reply.model;

import co.worker.board.domain.user.model.UserResult;
import co.worker.board.util.Word;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReplyResult {
    private Long seq;
    private String content;
    private UserResult user;
    private Long boardSeq;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = Word.KST)
    private LocalDateTime savedTime;
}
