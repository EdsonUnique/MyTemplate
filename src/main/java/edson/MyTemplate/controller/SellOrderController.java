package edson.MyTemplate.controller;
/*
import edson.MyTemplate.Entity.Order;
import edson.MyTemplate.core.OrderVO;
import edson.MyTemplate.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 卖家端订单管理
 */
/*
@Controller
@RequestMapping("/sellOrder")
public class SellOrderController {

    @Autowired
    private TemplateService productOrderService;

    /**
     * 分页查看卖家所有订单
     * @param pagenum
     * @param size
     * @param model 返回给页面的存储对象
     * @return
     */
/*
    @GetMapping("/view")
    public String view(@RequestParam(value = "pagenum",defaultValue = "1")Integer pagenum,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Model model){

        PageRequest pageRequest=PageRequest.of(pagenum-1,size);
        Page<Order> orderList=productOrderService.findAll(pageRequest);
        model.addAttribute("orderPage",orderList);
        model.addAttribute("currentPage",pagenum);

        System.out.println("view进来");

        return "view";

    }

    @GetMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable(value = "orderId")String orderId,
                              Model model){
        try{
            Order order=productOrderService.cancelOrder(orderId);
            model.addAttribute("msg","取消成功");
            model.addAttribute("orderId",order.getOrderId());
            model.addAttribute("successUrl","/sell/sellOrder/view");
            return "successOrder";
        }catch (Exception e){
            model.addAttribute("errorMsg",e.getMessage());
            model.addAttribute("errorUrl","/sell/sellOrder/view");
            return "/common/error";
        }

    }

    @GetMapping("/viewOrderItems/{orderId}")
    public String viewOrderItems(@PathVariable(value = "orderId")String orderId,
                              Model model){
        try{
            OrderVO orderVO=productOrderService.findByOrderId(orderId);
            model.addAttribute("orderVO",orderVO);
            return "orderItems";
        }catch (Exception e){
            model.addAttribute("errorMsg",e.getMessage());
            model.addAttribute("errorUrl","/sell/sellOrder/view");
            return "/common/error";
        }
    }

    @GetMapping("/finishOrder/{orderId}")
    public String finishOrder(@PathVariable(value = "orderId")String orderId,
                                 Model model){
        try{
            Order order=productOrderService.finishOrder(orderId);
            model.addAttribute("msg","完结成功");
            model.addAttribute("orderId",order.getOrderId());
            model.addAttribute("successUrl","/sell/sellOrder/view");
            return "successOrder";
        }catch (Exception e){
            model.addAttribute("errorMsg",e.getMessage());
            model.addAttribute("errorUrl","/sell/sellOrder/view");
            return "/common/error";
        }
    }


}*/
