package co.worker.board.domain.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "SessionData")
@NoArgsConstructor
public class SessionData {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "UserEntity_seq")
    private UserEntity userEntity;

    private LocalDateTime savedTime;

    public SessionData(UserEntity user){
        this.id = UUID.randomUUID().toString();
        this.userEntity = user;
        this.savedTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

}
