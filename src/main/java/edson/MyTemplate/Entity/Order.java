package edson.MyTemplate.Entity;

import edson.MyTemplate.enums.PayStatus;
import edson.MyTemplate.utils.GetPageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_master")
@DynamicUpdate//自动更新时间
public class Order {

    @Id
    @Column(name="order_id")
    private String orderId;

    @Column(name="buyer_name")
    private String buyerName;

    @Column(name="buyer_phone")
    private String buyerPhone;

    @Column(name="buyer_address")
    private String buyerAddress;

    @Column(name="buyer_openid")
    private String buyerOpenId;

    @Column(name="order_amount")
    private BigDecimal orderAmount;

//    @Column(name="order_status")
//    private Integer orderStatus= OrderStatus.NEW_ORDER.getCode();

    @Column(name="pay_status")
    private Integer payStatus= PayStatus.WAIT_PAY.getCode();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", updatable = false)
    @Generated(GenerationTime.INSERT)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time", updatable = false)
    @Generated(GenerationTime.INSERT)
    private Date updateTime;

//    public String getOrderStatusEnumMsg(){
//
//        return GetPageEnum.getPageEnum(orderStatus,OrderStatus.class).getMessage();
//    }

    public String getPayStatusEnumMsg(){
        return GetPageEnum.getPageEnum(payStatus,PayStatus.class).getMessage();
    }



}
