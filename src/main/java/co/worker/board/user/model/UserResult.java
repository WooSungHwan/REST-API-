package co.worker.board.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserResult {
    private Long seq;
    private String id;
    private String password;
    private String name;
}
