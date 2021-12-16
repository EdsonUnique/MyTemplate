package edson.MyTemplate.multiDataSource;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import oracle.jdbc.xa.client.OracleXADataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;

/**  数据源配置类   分布式事务  master是mysql数据库  slave是oracle数据库
 * @Author: yangxi
 * @Date: 2021/7/20 16:12
 */
@Configuration//定义为配置类
public class DataSourceConfiguration {

    @Value("${spring.datasource.master.jdbc-url}")
    private String master_url;
    @Value("${spring.datasource.master.username}")
    private String master_username;
    @Value("${spring.datasource.master.password}")
    private String master_password;
    // 注入数据源2配置项
    @Value("${spring.datasource.slave.jdbc-url}")
    private String slave_url;
    @Value("${spring.datasource.slave.username}")
    private String slave_username;
    @Value("${spring.datasource.slave.password}")
    private String slave_password;


    @Bean(name = "master")//将返回值注册为组件
//    @ConfigurationProperties("spring.datasource.master")//指定配置前缀
    public DataSource master(){
        MysqlXADataSource dataSource = new MysqlXADataSource();
        dataSource.setUrl(master_url);
        dataSource.setUser(master_username);
        dataSource.setPassword(master_password);
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(dataSource);
        atomikosDataSourceBean.setUniqueResourceName("master");
        return atomikosDataSourceBean;
    }

    @Bean(name = "slave")
//    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slave(){
        try{
            OracleXADataSource dataSource = new OracleXADataSource();
            dataSource.setURL(slave_url);
            dataSource.setUser(slave_username);
            dataSource.setPassword(slave_password);
            AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
            atomikosDataSourceBean.setXaDataSource(dataSource);
            atomikosDataSourceBean.setUniqueResourceName("slave");
            return atomikosDataSourceBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(DataSource master, DataSource slave) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", master);
        targetDataSources.put("slave", slave);
        return new DynamicDataSource(master, targetDataSources);
    }

    /**
     * 分布式事务管理器
     */
    @Bean(name = "transactionManager")
    public JtaTransactionManager jtaTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

}
