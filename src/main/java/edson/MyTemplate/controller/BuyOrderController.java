package edson.MyTemplate.controller;

import edson.MyTemplate.Entity.Order;
import edson.MyTemplate.VO.OrderVO;
import edson.MyTemplate.VO.RestVO;
import edson.MyTemplate.VO.RestWrapper;
import edson.MyTemplate.exception.MyException;
import edson.MyTemplate.exception.MyExceptionEnum;
import edson.MyTemplate.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.stream.IntStream.of;

/**
 * 订单
 */
@RestController
@RequestMapping("/buyOrder")
@Slf4j
public class BuyOrderController {

    @Autowired
    private TemplateService productOrderService;

   /*   //创建订单
    @PostMapping("/create")
    public RestVO create(@Valid @RequestBody OrderForm orderForm,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //校验出错
            log.error("参数错误："+bindingResult.getFieldError().getDefaultMessage());
            throw new MyException(MyExceptionEnum.ORDER_PARAM_WRONG.getCode()
                    ,bindingResult.getFieldError().getDefaultMessage());
        }

        OrderVO orderVO=new OrderVO();
        orderVO.setBuyerOpenId(orderForm.getOpenid());
        orderVO.setBuyerPhone(orderForm.getPhone());
        orderVO.setBuyerAddress(orderForm.getAddress());
        orderVO.setBuyerName(orderForm.getName());

        //json转List
      List<OrderItem> orderItemList=JSONArray.parseArray(orderForm.getItems(), OrderItem.class);
        orderVO.setOrderItemList(orderItemList);

        Order order=productOrderService.createOrder(orderVO);

        Map<String,String> data=new HashMap<String,String>();
        data.put("orderId",order.getOrderId());

        return RestWrapper.success();
    }
*/
    //查看所有订单
    @GetMapping("/list")

    public RestVO list(@RequestParam("openid")String openid,
                       @RequestParam(value = "pagenum",defaultValue = "0") Integer pagenum,
                       @RequestParam(value = "size",defaultValue = "10") Integer size){

        if(StringUtils.isEmpty(openid)){
            log.error("【买家微信号不能为空】");
            throw new MyException(MyExceptionEnum.ORDER_PARAM_WRONG);
        }

        PageRequest pageRequest=PageRequest.of(pagenum,size);
        Page<Order> orderPage=productOrderService.findAll(openid,pageRequest);

        return RestWrapper.success(orderPage.getContent());

    }

    //查看订单详情
    @GetMapping("/view_detail/{id}")
    public RestVO viewOrderItems(@PathVariable("id") String orderId){

        OrderVO orderVO=productOrderService.findByOrderId(orderId);

        return RestWrapper.success();

    }

    //取消订单
    @GetMapping("cancle_order/{id}")
    public RestVO cancleOrder(@PathVariable("id") String orderId){
        //TODO 改进 任意订单号都能访问
        Order order=productOrderService.cancelOrder(orderId);

        return RestWrapper.success(order);
    }

    //支付订单

    //完结订单


}
