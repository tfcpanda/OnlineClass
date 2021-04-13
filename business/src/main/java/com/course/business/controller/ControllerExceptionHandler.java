package com.course.business.controller;

import com.course.server.dto.ResponseDto;
import com.course.server.ecxeption.BusinessException;
import com.course.server.ecxeption.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 田付成
 * @date 2021/3/16 21:35
 *
 * 通用异常处理器，可以对后端异常进行处理，
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    //对后端验证进行异常处理，所有的validatorException的异常都会被拦截。
    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e) {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        LOG.warn(e.getMessage());
        responseDto.setMessage("请求参数异常");
        return responseDto;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseDto businessExceptionHandler(BusinessException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        LOG.error("业务异常：{}", e.getCode().getDesc());
        responseDto.setMessage(e.getCode().getDesc());
        return responseDto;
    }
}
