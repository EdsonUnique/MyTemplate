package edson.MyTemplate.form;


import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ProductForm{

    private String productId;

    @NotEmpty(message = "商品名称不能为空")
    @Size(max = 15,min = 1,message = "商品名称长度在1-15个字符之内")
    private String productName;

    @NotNull(message = "商品价格不能为空")
    @DecimalMin(value = "0",message = "商品价格形式不正确")
    private BigDecimal productPrice;

    @NotNull(message = "商品库存不能为空")
    private Integer productStock;

    @NotEmpty(message = "商品描述不能为空")
    @Size(max = 200,min=10,message = "商品描述长度为10-200字符")
    private String productDescription;

    /*
    商品图片的链接地址
     */
    @NotEmpty(message = "商品图片不能为空")
    private String productIcon;

    @NotNull(message = "商品类别不能为空")
    private Integer categoryType;

    @NotNull(message = "商品状态不能为空")
    private Integer productStatus;


}
