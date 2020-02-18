package co.worker.board.user.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResult {
    private Long seq;
    private String id;
    private String password;
    private String name;
}
