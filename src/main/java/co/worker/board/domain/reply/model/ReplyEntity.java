package co.worker.board.domain.reply.model;

import co.worker.board.domain.user.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ReplyEntity")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "seq")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String content;
    @ManyToOne
    @JoinColumn(name = "UserEntity_seq")
    private UserEntity user;
    private Long boardSeq;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime saved_time;
}
