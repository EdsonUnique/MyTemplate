package edson.MyTemplate.enums;

import lombok.Getter;

/**
 * 支付状态
 */
public enum PayStatus implements PageEnum{

    WAIT_PAY(0,"待支付"),
    SUCCESS_PAY(1,"已支付");

    @Getter
    private Integer code;

    @Getter
    private String message;

    PayStatus(Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
