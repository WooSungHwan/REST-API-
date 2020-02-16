package co.worker.board.board.model;

import co.worker.board.user.model.UserEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "BoardEntity")
@Builder
@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue
    Long seq;
    String content;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserEntity_seq")
    UserEntity userEntity;
    String title;

    public BoardEntity(Long seq, String content, UserEntity userEntity, String title){
        this.seq = seq;
        this.content = content;
        this.userEntity = userEntity;
        this.title = title;
    }
}
