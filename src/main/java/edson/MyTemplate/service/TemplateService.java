package edson.MyTemplate.service;

import edson.MyTemplate.Entity.Order;
import edson.MyTemplate.core.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateService {

    //创建订单
    Order createOrder(OrderVO orderVO);

    //删除订单
    Order deleteOrder(Order order);

    //查询订单
    Page<Order> findAll(String openid,Pageable pageable);

    Page<Order> findAll(Pageable pageable);

    OrderVO findByOrderId(String orderId);

    //取消订单
    Order cancelOrder(String orderId);

    //完结订单
    Order finishOrder(String orderId);

    //支付订单
    Order payOrder(String orderId);
}
