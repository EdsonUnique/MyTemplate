package edson.MyTemplate.security;

import edson.MyTemplate.constant.CookieConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截
 */
@Component
public class MyAuth implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*Cookie[] cookies=request.getCookies();
        if(null!=cookies && cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals(CookieConstant.TOKEN_KEY)){
                    String value=stringRedisTemplate.opsForValue().get(cookie.getValue());
                    if(StringUtils.isEmpty(value)){
                        //未登录
                        throw new SellerException(SellerExceptionEnum.SELLER_NOT_LOGIN);
                    }
                }
            }
            return true;
        }
        throw new SellerException(SellerExceptionEnum.SELLER_NOT_LOGIN);*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {



    }
}
