package co.worker.board.domain.etc.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "seq")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FileEntity")
public class FileVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String filename;
}
