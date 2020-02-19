package co.worker.board.user.model;

import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime savedTime;
}
