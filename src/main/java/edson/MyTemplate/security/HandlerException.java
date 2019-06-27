package edson.MyTemplate.security;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常处理
 */
@ControllerAdvice
public class HandlerException {
/*
    @ExceptionHandler(SellerException.class)//需要捕获的异常类
    public String handleLoginException(Exception e){
        if(e instanceof SellerException){
            e.printStackTrace();
            return "login";
        }
        return "";
    }

*/
}
