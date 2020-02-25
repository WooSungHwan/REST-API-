package co.worker.board.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ErrorResponse{
    private Integer code;
    private String message;
}
