package co.worker.board.user.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "seq")
@Entity
@Table(name = "UserEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id @GeneratedValue
    private Long seq;
    private String id;
    private String name;
    private String password;
    private LocalDateTime savedTime;
}
