package co.worker.board.domain.favorite.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "FavoriteEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEntity {
    /* 즐겨찾기 */
    @Id
    @GeneratedValue
    private Long seq;
    private Long boardSeq;
    private Long userSeq;
}
