package co.worker.board.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class RestResponse<T> {
    private int code;
    private String message;
    private T result;
}
