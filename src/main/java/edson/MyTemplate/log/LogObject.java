package edson.MyTemplate.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志信息对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogObject {

    /**
     * 用户id
     */
    private Long UserId;

    /**
     * 动作类型
     */
    private String actionType;

    /**
     * 当前时间
     */
    private Long timestamp;

    /**
     * 客户端ip地址
     */
    private String IP;

    /**
     * 日志信息
     */
    private Object info=null;

}
