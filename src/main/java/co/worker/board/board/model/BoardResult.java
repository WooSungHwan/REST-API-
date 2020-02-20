package co.worker.board.board.model;

import co.worker.board.user.model.UserEntity;
import co.worker.board.user.model.UserResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
public class BoardResult {
    Long seq;
    String content;
    UserResult user;
    String title;
    LocalDateTime savedTime;
}
