package edson.MyTemplate.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志生成器
 */
@Slf4j
public class LogGenenrator {

    /**
     * 生成日志
     * @param request 请求信息 获取IP地址
     * @param userId 用户id
     * @param action 动作类型
     * @param info 日志信息
     */
    public static void generateLog(HttpServletRequest request,Long userId,String action,Object info){
        log.info(
                JSON.toJSONString(
                        new LogObject(userId,action,System.currentTimeMillis(),request.getRemoteAddr(),info)
                )
        );
    }


}
