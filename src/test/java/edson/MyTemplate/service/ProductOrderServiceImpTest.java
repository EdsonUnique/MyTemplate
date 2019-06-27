package edson.MyTemplate.service;

import com.alibaba.fastjson.JSON;
import edson.MyTemplate.Entity.Order;
import edson.MyTemplate.Entity.OrderItem;
import edson.MyTemplate.VO.OrderItemVO;
import edson.MyTemplate.VO.OrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductOrderServiceImpTest {

    @Autowired
    TemplateService productOrderService;

    @Test
    public void createOrder() {

        OrderVO orderVO=new OrderVO();

        List<OrderItem> orderItemList=new ArrayList<OrderItem>();
        OrderItemVO orderItemVO=new OrderItemVO();
        orderItemVO.setProductId("1");
        orderItemVO.setProductQuantity(1);
        orderItemList.add(orderItemVO);

        orderVO.setOrderItemList(orderItemList);

        orderVO.setBuyerName("ttt");
        orderVO.setBuyerAddress("weew");
        orderVO.setBuyerPhone("13547415847");
        orderVO.setBuyerOpenId("111");
        productOrderService.createOrder(orderVO);

    }

    @Test
    public void findByOrderId(){

        Order order=productOrderService.findByOrderId("15604942811510.29060");
        System.out.println(JSON.toJSON(order));
    }

    @Test
    public void cancleOrder(){

        Order order=productOrderService.cancelOrder("1560740127655KngvMbs");
        System.out.println(JSON.toJSON(order));
    }

    @Test
    public void payOrder(){

        Order order=productOrderService.payOrder("1560740127655KngvMbs");
        System.out.println(JSON.toJSON(order));
    }

    @Test
    public void finishOrder(){

        Order order=productOrderService.finishOrder("1560740127655KngvMbs");
        System.out.println(JSON.toJSON(order));
    }


}