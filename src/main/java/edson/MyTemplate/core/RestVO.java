package edson.MyTemplate.core;

import lombok.Data;

/*
    前端数据，JSON格式，先存储为对象后通过注解转化为JSON数据
 */
@Data
public class RestVO<T> {
    /*
    错误码
     */
    private Integer code;

    private String msg;

    private T data;

}
