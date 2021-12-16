package edson.MyTemplate.dao;

import edson.MyTemplate.Entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: yangxi
 * @Date: 2021/12/13 16:04
 */
@Repository
public interface UserDao{

    User selectById(@Param("id") String id);

    Integer update(@Param("user") User user);

    Integer insert(@Param("user") User user);

}
