package edson.MyTemplate.controller;

import edson.MyTemplate.core.RestVO;
import edson.MyTemplate.core.RestWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangxi
 * @Date: 2022/4/26 16:11
 */
@RestController
@RequestMapping("/security")
public class SpringSecurityController {

    @RequestMapping("/test")
    public RestVO getSecurity(){
        return RestWrapper.success("security");
    }
}
