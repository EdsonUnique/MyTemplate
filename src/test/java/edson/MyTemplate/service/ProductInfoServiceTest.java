package edson.MyTemplate.service;

import com.alibaba.fastjson.JSON;
import edson.MyTemplate.Entity.ProductInfo;
import edson.MyTemplate.enums.ProductStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void test1(){
        //System.out.println(JSON.toJSONString(productInfoService.selectProductById("1")));
        PageRequest pageRequest=new PageRequest(0,1);//pageable是接口，pageRequest实现了该接口
        System.out.println("haah"+JSON.toJSONString(productInfoService.findAll(pageRequest).getContent()));
    }

    @Test
    public void saveOne(){

        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("苹果");
        productInfo.setCategoryType(1);
        productInfo.setProductStock(40);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("sjjsjs");
        productInfo.setProductStatus(ProductStatus.UP.getCode());
        productInfo.setProductPrice(new BigDecimal(22));

        productInfoService.saveOne(productInfo);

    }
}