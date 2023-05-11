package com.haitaos.exception;


import com.haitaos.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JsonData handler(RuntimeException e) {
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            log.error("[Business Exception]{}", e.getMessage());
            return JsonData.buildCodeAndMsg(bizException.getCode(), bizException.getMsg());
        } else {
            log.error("[System Exception]{}", e.getMessage());
            return JsonData.buildError( "System Exception");
        }
    }
}


