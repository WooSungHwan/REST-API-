package co.worker.board.configuration.controlleradvice;

import co.worker.board.configuration.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestControllerAdvice
@Slf4j
public class RestControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handlerRuntimeException(RuntimeException e, HttpServletRequest req){
        log.error("================= Handler RuntimeException =================");
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "RuntimeException : "+e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req){
        log.error("================= Handler MethodArgumentNotValidException =================");
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "MethodArgumentNotValidException : "+e.getMessage());
    }
}
