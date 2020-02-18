package co.worker.board.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Builder
public class UserParam {
    private Long seq;
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
}
