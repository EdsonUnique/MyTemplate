package edson.MyTemplate;

import edson.MyTemplate.security.MyAuth;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@MapperScan(value = {"edson.MyTemplate.dao"})
@SpringBootApplication
public class MyTemplateApplication implements WebMvcConfigurer {

    @Autowired
    private MyAuth sellerAuth;

    public static void main(String[] args) {
        SpringApplication.run(MyTemplateApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathList=new ArrayList<String>();

        pathList.add("/buyOrder/*");
        pathList.add("/buyProduct/*");
        pathList.add("/product/*");
        pathList.add("/sellOrder/*");
        pathList.add("/seller/logout");

        registry.addInterceptor(sellerAuth).addPathPatterns(pathList);
    }
}
