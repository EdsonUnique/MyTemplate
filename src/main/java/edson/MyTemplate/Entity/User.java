package edson.MyTemplate.Entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: yangxi
 * @Date: 2021/12/7 13:53
 */
@Data
public class User {

    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("电子邮箱")
    private String email;

    @ExcelProperty("出生日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date birth;
}
