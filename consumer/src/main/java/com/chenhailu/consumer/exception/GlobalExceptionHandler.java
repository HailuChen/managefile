package com.chenhailu.consumer.exception;


import com.chenhailu.consumer.constant.Constant;
import com.chenhailu.consumer.controller.FileReadController;
import com.chenhailu.consumer.entity.ReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReadController.class);

    @ExceptionHandler(value =Exception.class)
    public ReturnMessage handler(Exception e) {
        LOGGER.error(e.getMessage());
        return new ReturnMessage(Constant.RET_CODE_FILE_READ_FAIL, Constant.RET_MSG_FILE_READ_FAIL);
    }

}
