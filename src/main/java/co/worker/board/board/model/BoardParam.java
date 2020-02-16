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
    UserEntity user;
    @NotEmpty
    String title;
}
