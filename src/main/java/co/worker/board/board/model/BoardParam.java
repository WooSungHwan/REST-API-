package co.worker.board.board.model;

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
    @Min(0)
    Long seq;
    @NotEmpty
    String content;
    @NotEmpty
    String username;
    @NotEmpty
    String title;
}
