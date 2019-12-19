package edson.MyTemplate.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterCORS implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse resp = (HttpServletResponse)servletResponse;
//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        resp.addHeader("Access-Control-Allow-Headers","Access-Control-Allow-Origin");
//        resp.addHeader("Access-Control-Allow-Headers"," x-requested-with");
//        resp.setHeader("Access-Control-Allow-Methods", "*");
//        resp.setHeader("Access-Control-Allow-Credentials","true");

        //OPTION请求就直接返回
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        if (req.getMethod().equals("OPTIONS")) {
//            resp.setStatus(200);
//            resp.flushBuffer();
//        }else {
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        response.setHeader("Access-Control-Allow-Credentials","true"); //是否允许浏览器携带用户身份信息（cookie）
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(200);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
