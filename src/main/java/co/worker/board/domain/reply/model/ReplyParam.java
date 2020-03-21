package co.worker.board.domain.reply.model;

import co.worker.board.domain.user.model.UserParam;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyParam {
    @Min(1)
    private Long seq;
    @NotEmpty
    private String content;
    @NotNull
    private UserParam user;
    @NotNull
    private Long boardSeq;
    private LocalDateTime savedTime;
}
