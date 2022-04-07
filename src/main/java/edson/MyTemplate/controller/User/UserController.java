package edson.MyTemplate.controller.User;

import edson.MyTemplate.core.RestVO;
import edson.MyTemplate.core.RestWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: yangxi
 * @Date: 2022/4/7 11:14
 */
@Controller
@RequestMapping("/user")
@Api(tags = "UserController",description = "用户管理")
public class UserController {

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public RestVO getUser(){

        return RestWrapper.success();
    }
}
