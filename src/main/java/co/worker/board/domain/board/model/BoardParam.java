package co.worker.board.domain.board.model;

import co.worker.board.domain.user.model.UserParam;
import co.worker.board.util.Word;
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
    LocalDateTime savedTime = LocalDateTime.now(ZoneId.of(Word.KST));
}
