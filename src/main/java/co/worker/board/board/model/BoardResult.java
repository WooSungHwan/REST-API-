package co.worker.board.board.model;

import co.worker.board.user.model.UserEntity;
import co.worker.board.user.model.UserResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
public class BoardResult {
    private Long seq;
    private String content;
    private UserResult user;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime savedTime;
}
