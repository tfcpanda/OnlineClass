package com.course.server.ecxeption;

/**
 * @author 田付成
 * @date 2021/3/16 21:26
 */
public class ValidatorException extends RuntimeException {

    public ValidatorException(String message){
        super(message);
    }

}
