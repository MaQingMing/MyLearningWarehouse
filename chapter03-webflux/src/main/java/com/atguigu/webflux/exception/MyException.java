package com.atguigu.webflux.exception;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/3 21:08
 */
@Controller
public class MyException {

    @ExceptionHandler(Exception.class)
    public String exception(){
        return "错误异常";
    };
}
