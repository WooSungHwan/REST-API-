package co.worker.board.reply.model;

import co.worker.board.user.model.UserResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReplyResult {
    private Long seq;
    private String content;
    private UserResult user;
    private Long boardSeq;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime saved_time;
}
