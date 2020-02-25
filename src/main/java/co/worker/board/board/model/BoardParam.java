package co.worker.board.board.model;

import co.worker.board.user.model.UserEntity;
import co.worker.board.user.model.UserParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
@Builder
public class BoardParam {
    @Min(1)
    Long seq;
    @NotEmpty
    String content;
    @NotNull
    UserParam user;
    @NotEmpty
    String title;
    LocalDateTime savedTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
}
