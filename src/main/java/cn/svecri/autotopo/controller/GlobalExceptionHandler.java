package cn.svecri.autotopo.controller;

import cn.svecri.autotopo.vo.ResponseVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler()
    protected ResponseVo<Object> handleAllOtherException(Exception e) {
        log.error("Unexpected Error Happened", e);
        return ResponseVo.error(ResponseVo.UNKNOWN, e);
    }

}
