package edson.MyTemplate.multiDataSource;

/**
 * @Author: yangxi
 * @Date: 2021/12/13 15:35
 */

import java.lang.annotation.*;

/**
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DS {
    String value();
}
