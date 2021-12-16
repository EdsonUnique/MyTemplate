package edson.MyTemplate.service;

import edson.MyTemplate.Entity.User;
import edson.MyTemplate.MyTemplateApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yangxi
 * @Date: 2021/12/13 16:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyTemplateApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        System.out.println("---come in----");
//        User slave = userService.selectSlaveUserById("2");
//        User master = userService.selectMasterUserById("2");
//
//        System.out.println(master);
//        master.setName("改变change4444");
//        System.out.println(master);
//
//        userService.updateMaster(master);
//
//        System.out.println(slave);
//        System.out.println(master);

//        User slave = master;
//        slave.setId(1);
//        slave.setName("Oracle");

//        userService.updateSlave(slave);
        userService.save();

    }

}