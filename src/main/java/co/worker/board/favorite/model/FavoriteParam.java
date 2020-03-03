package co.worker.board.favorite.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteParam {
    /* 즐겨찾기 */
    private Long seq;
    @NotNull @Min(1)
    private Long boardSeq;
    @NotNull @Min(1)
    private Long userSeq;
}
