package edson.MyTemplate.multiDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/** 数据源 的 mybatis 配置类
 * @Author: yangxi
 * @Date: 2021/7/20 16:26
 */
@Configuration
@MapperScan(value = "edson.MyTemplate.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfiguration {

    @Autowired  //自动装配
    @Qualifier("dynamicDataSource") // 指定装配的名称
    private DataSource db;
    @Autowired
    private MultiDataSourceTransactionFactory multiDataSourceTransactionFactory;

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db);//设置动态数据源
        //设置事务（重写）
        sqlSessionFactoryBean.setTransactionFactory(multiDataSourceTransactionFactory);
        sqlSessionFactoryBean//指定mapper文件路径
                .setMapperLocations(
                        new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
