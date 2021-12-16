package edson.MyTemplate.multiDataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Author: yangxi
 * @Date: 2021/12/16 15:04
 */
@Component
public class MultiDataSourceTransactionFactory extends SpringManagedTransactionFactory {
    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new MultiDataSourceTransaction(dataSource);
    }

}
