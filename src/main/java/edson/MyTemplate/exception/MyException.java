package edson.MyTemplate.exception;

import lombok.Getter;

public class MyException extends RuntimeException {

    /**
     * 异常码
     */
    @Getter
    private Integer code;

    public MyException(MyExceptionEnum orderExceptionEnum){
        super(orderExceptionEnum.getMessage());
        this.code=orderExceptionEnum.getCode();
    }

    public MyException(Integer code, String message){
        super(message);
        this.code=code;
    }

    public MyException(String message){
        super(message);
    }

}
