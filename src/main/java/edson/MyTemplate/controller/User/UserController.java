package edson.MyTemplate.controller.User;

import edson.MyTemplate.constant.RedisConstant;
import edson.MyTemplate.core.RestVO;
import edson.MyTemplate.core.RestWrapper;
import edson.MyTemplate.redis.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangxi
 * @Date: 2022/4/7 11:14
 */
@RestController
@RequestMapping("/user")
@Api(tags = "UserController",description = "用户管理")
public class UserController {

    @ApiOperation(value = "获取用户信息",response = RestVO.class)
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public RestVO getUser(){

        System.out.println("----ff---");

        RedisUtil.set(RedisConstant.KEY_PREFIX + "test","edson");
        String username = RedisUtil.get(RedisConstant.KEY_PREFIX + "test");
        return RestWrapper.success(username);
    }
}
