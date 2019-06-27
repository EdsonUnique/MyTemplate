package edson.MyTemplate.exception;

import lombok.Getter;

/**
 * 订单异常枚举类定义
 */
public enum MyExceptionEnum {

    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_SHORTAGE(2,"库存不足"),

    PRODUCT_ORDER_NOT_EXIST(3,"订单不存在"),
    PRODUCT_ORDERITEM_NOT_EXIST(4,"订单项不存在"),
    ORDER_CANCLE_WRONG(5,"订单已完结或已取消"),
    ORDER_CANCLE_FAIL(6,"订单取消失败"),
    ORDER_STATUS_WRONG(7,"订单状态错误"),
    ORDER_FINISH_FAIL(8,"订单完结失败"),
    ORDER_PAY_FAIL(9,"订单支付失败"),
    PAY_STATUS_WRONG(10,"支付状态不正确"),
    ORDER_PARAM_WRONG(11,"订单参数错误")
    ;
    @Getter
    private Integer code;
    @Getter
    private String message;

    MyExceptionEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }

}
