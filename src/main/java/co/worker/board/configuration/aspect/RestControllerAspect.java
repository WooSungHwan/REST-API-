package co.worker.board.configuration.aspect;

import co.worker.board.configuration.response.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RestControllerAspect {

    @Around("execution(* co.worker.board.*.controller.*.*(..))")
    public RestResponse<Object> restResponseHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        return new RestResponse<>(HttpStatus.OK.value(), "success", joinPoint.proceed());
    }
}