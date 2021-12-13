package edson.MyTemplate.controller.excel;

import com.alibaba.excel.support.ExcelTypeEnum;
import edson.MyTemplate.Entity.User;
import edson.MyTemplate.utils.EasyExcel.EasyExcelUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Author: yangxi
 * @Date: 2021/12/7 13:48
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @PostMapping(value = "/importExcel")
    public void readExcel(@RequestBody MultipartFile file) throws Exception{
        List<User> userList = EasyExcelUtil.readExcel(file.getInputStream(),User.class);
        userList.forEach(f->System.out.println(f.getBirth()));
        System.out.println(userList.size());
    }

    @PostMapping("/exportExcel")
    public void writeExcel(HttpServletResponse response){

        //模拟需要导出的数据
        List<User> userList = new ArrayList<>();

//        User user1 = new User();
//        user1.setId(1245354);
//        user1.setAge(18);
//        user1.setName("zhangsaxxsccn");
//        user1.setEmail("EMAILccccccccedsaasxs");
//        userList.add(user1);

        for(int i = 0; i < 5 ;i++){
            User user = new User();
            user.setId(i+1);
            user.setAge(i+18);
            user.setName("zhangsan" + i);
            user.setEmail("EMAIL" + i);
            userList.add(user);
        }

        Set<String> columnSet = new HashSet<>();
        columnSet.add("age");
        columnSet.add("name");
        columnSet.add("email");
        String fileName = "test.xlsx";
        String sheetName = "人员信息";

        try {
            EasyExcelUtil.writeExcel(response,userList,columnSet,fileName,sheetName,ExcelTypeEnum.XLSX);
        }catch (Exception e){
            System.out.println("导出失败！");
        }


    }

    @GetMapping("/test")
    public void test(){
        System.out.println("come in");
    }

}
