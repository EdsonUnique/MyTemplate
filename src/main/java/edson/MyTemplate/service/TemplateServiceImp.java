package edson.MyTemplate.service;

import edson.MyTemplate.Entity.Order;
import edson.MyTemplate.VO.OrderVO;
import edson.MyTemplate.controller.WebSocketService;
import edson.MyTemplate.enums.OrderStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TemplateServiceImp implements TemplateService {


    @Autowired
    WebSocketService webSocketService;

    @Override
    @Transactional//事务的注解 要么全部做 要么全部不做  出错将回滚
    public Order createOrder(OrderVO orderVO) {
        Order order=new Order();
        BeanUtils.copyProperties(orderVO,order);


        //订单创建成功，发送websocket消息
        webSocketService.sendMessage("您有新订单！");

        return null;
    }

    @Override
    public Order deleteOrder(Order order) {
        return null;
    }

    @Override
    public Page<Order> findAll(String openid,Pageable pageable) {
        //不关联查询订单详情，当查看订单详情时调用findByOrderId方法
        //openid 买家微信号
        Order order=new Order();//构造条件
        order.setPayStatus(null);
        order.setOrderStatus(null);
        order.setBuyerOpenId(openid);
        Example<Order> example=Example.of(order);
        //Page<Order> orderPage=null.findAll(example,pageable);
        return null;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public OrderVO findByOrderId(String orderId) {
        return null;
    }

    @Override
    public Order cancelOrder(String orderId) {
        return null;
    }

    @Override
    public Order finishOrder(String orderId) {
        return null;
    }

    @Override
    public Order payOrder(String orderId) {
        return null;
    }


}
