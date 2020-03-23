package co.worker.board.domain.user.model;

import co.worker.board.util.Word;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String userId;
    private String name;
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone= Word.KST)
    private LocalDateTime savedTime;
    private int role;
}
