package edson.MyTemplate.service;

import com.alibaba.fastjson.JSON;
import edson.MyTemplate.Entity.Seller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceTest {

    @Autowired
    private SellerService sellerService;

    @Test
    public void saveOne(){

        Seller seller=new Seller();
        seller.setUsername("liming");
        seller.setPassword("12121");
        seller.setOpenid("12121");

        System.out.println(JSON.toJSON(sellerService.saveOne(seller)));
    }

    @Test
    public void findOneByOpenid(){
        String openid="12121";

        System.out.println(JSON.toJSONString(sellerService.findOneByOpenid(openid)));
    }




}