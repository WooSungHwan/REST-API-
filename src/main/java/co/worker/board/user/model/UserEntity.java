package co.worker.board.user.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "seq")
@Entity
@Table(name = "UserEntity")
@Builder
@NoArgsConstructor
public class UserEntity {
    @Id @GeneratedValue
    private Long seq;
    private String id;
    private String name;
    private String password;
    private LocalDateTime savedTime;

    public UserEntity(Long seq, String id, String name, String password, LocalDateTime savedTime){
        this.seq = seq;
        this.id = id;
        this.name = name;
        this.password = password;
        this.savedTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
