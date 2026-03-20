package com.chder.hydrology_calculate.exception;

import com.chder.hydrology_calculate.enums.ErrorMsg;
import com.chder.hydrology_calculate.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handle(Exception e){
        return Result.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
