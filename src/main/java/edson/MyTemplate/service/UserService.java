package edson.MyTemplate.service;

import edson.MyTemplate.Entity.User;
import edson.MyTemplate.dao.UserDao;
import edson.MyTemplate.multiDataSource.DS;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yangxi
 * @Date: 2021/12/13 16:02
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public void save(){


        //动态代理  解决service内部方法调用aop失效问题
        UserService aopUserService =  ((UserService)AopContext.currentProxy());

        User master = aopUserService.selectMasterUserById("1");
        master.setName("cha012210000");
        aopUserService.updateMaster(master);


        User slave = master;
        slave.setId(1);
        slave.setName("change failfffffvvvds");
        aopUserService.updateSlave(slave);


    }

    @DS("slave")
    public User selectSlaveUserById(String id){
        return userDao.selectById(id);
    }

    @DS("master")
    public User selectMasterUserById(String id){
        return userDao.selectById(id);
    }

    @DS("master")
    public void updateMaster(User user){
         userDao.update(user);
    }

    @DS("slave")
    public void updateSlave(User user){
        System.out.println("---开始updateSlave----");
        userDao.update(user);
        System.out.println("---结束updateSlave----");
    }

//    @DS("slave")
    public void insertSlave(User user){
        Integer res = userDao.insert(user);
    }


}
