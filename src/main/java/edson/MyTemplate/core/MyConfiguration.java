package book.core;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义配置类
 */
@Component
@Data
public class MyConfiguration {

    @Value("${upload.location}")
    public String uploadLocation;

}
