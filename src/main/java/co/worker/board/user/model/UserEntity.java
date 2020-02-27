package co.worker.board.user.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "seq")
@Entity
@Table(name = "UserEntity")
@Builder
@NoArgsConstructor
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String userId;
    private String name;
    private String password;
    private LocalDateTime savedTime;
    private int role;

    public UserEntity(Long seq, String id, String name, String password, LocalDateTime savedTime, int role){
        this.seq = seq;
        this.userId = id;
        this.name = name;
        this.password = password;
        this.savedTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        this.role = role;
    }

}
