package co.worker.board.domain.board.model;

import co.worker.board.domain.user.model.UserEntity;
import co.worker.board.util.Word;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
@Entity
@Table(name = "BoardEntity")
@Builder
@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;
    String content;
    @ManyToOne
    @JoinColumn(name = "UserEntity_seq")
    UserEntity userEntity;
    String title;
    LocalDateTime savedTime;

    public BoardEntity(Long seq, String content, UserEntity userEntity, String title, LocalDateTime savedTime){
        this.seq = seq;
        this.content = content;
        this.userEntity = userEntity;
        this.title = title;
        this.savedTime = LocalDateTime.now(ZoneId.of(Word.KST));
    }
}
