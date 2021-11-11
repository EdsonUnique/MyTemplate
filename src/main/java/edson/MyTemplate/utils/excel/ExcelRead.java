package edson.MyTemplate.utils.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 读取指定excel文件内容
 */
public class ExcelRead {

    /**
     * @param fileName  文件名
     * @param is        文件流
     * @param clazz     bean类型
     * @param startLine 从第几行开始读取数据 例如第1行开始读取 startLine=0
     *                  checkColumn  哪几列需要做判空校验 1 表示第一列
     *                  errorLine  用于返回错误数据的行数
     *                  totalSheet  需要导入数据的sheet数
     * @return
     */
    public static List<?> getExcelListInfo(String fileName, InputStream is, Class clazz, Integer startLine,List<Integer> checkColumn, List<String> errorLine, Integer totalSheet) {
        //初始化客户信息的集合
        List<Object> objectList = new ArrayList<Object>();
        if(null == checkColumn){
            checkColumn = new ArrayList<>();
        }
        if(null == errorLine){
            errorLine = new ArrayList<>();
        }
        try {

            //验证文件名是否合格
            if (!validateExcel(fileName)) {
                return null;
            }
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }

            //根据excel里面的内容读取客户信息
            objectList = getExcelInfo(is, isExcel2003, clazz, startLine,checkColumn, errorLine, totalSheet);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return objectList;
    }

    public static List<Object> getExcelInfo(InputStream is, boolean isExcel2003, Class clazz, Integer startLine,List<Integer> checkColumn, List<String> errorLine, Integer totalSheet) {
        List<Object> customerList = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            //当excel是2003时
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面客户的信息
            customerList = readExcelValue(wb, clazz, startLine,checkColumn, errorLine, totalSheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public static List<Object> readExcelValue(Workbook wb, Class clazz, Integer startLine, List<Integer> checkColumn, List<String> errorLine, Integer totalSheet) {
        List<Object> customerList = new ArrayList<Object>();
        try {
            Object classObj = Class.forName(clazz.getName()).newInstance();
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = clazz.getDeclaredFields();
            // 获取sheet数目
            for (int t = 0; t < totalSheet; t++) {
                Sheet sheet = wb.getSheetAt(t);
                Row row = null;
                int lastRowNum = sheet.getLastRowNum();

                // 循环读取
                for (int i = startLine; i <= lastRowNum; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        classObj = Class.forName(clazz.getName()).newInstance();
//                      //判断列数
                        Row rowHeader = startLine ==0 ? sheet.getRow(startLine) : sheet.getRow(startLine - 1);//获取列名那一栏的列数
                        if (rowHeader.getLastCellNum() != row.getLastCellNum()) {
                            errorLine.add("第" + i + "行数据不能为空");
                            continue;
                        }
                        // 获取每一列的值
                        for (int j = 0; j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            String value = getCellValue(cell);
                            Field field = fields[j];
                            String fieldName = field.getName();
                            System.out.print(value + " | ");
                            if (checkColumn.contains(j+1) && !StringUtils.isNotEmpty(value)) {//判空
                                errorLine.add("第" + (i+startLine) + "行" + (j+startLine) + "列数据不能为空！");
                                break;
                            }

                            String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                            Method setMethod = clazz.getDeclaredMethod(methodName, new Class[]{String.class});
                            setMethod.invoke(classObj, new Object[]{value});
                        }
                        System.out.println();
                        customerList.add(classObj);

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return customerList;
    }


    /***
     * 读取单元格的值
     *
     * @Title: getCellValue
     * @Date : 2014-9-11 上午10:52:07
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_NUMERIC:// 数字类型
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                        SimpleDateFormat sdf = null;
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                                .getBuiltinFormat("h:mm")) {
                            sdf = new SimpleDateFormat("HH:mm");
                        } else {// 日期
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                        }
                        Date date = cell.getDateCellValue();
                        result = sdf.format(date);
                    } else if (cell.getCellStyle().getDataFormat() == 58) {
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = org.apache.poi.ss.usermodel.DateUtil
                                .getJavaDate(value);
                        result = sdf.format(date);
                    } else {
                        double value = cell.getNumericCellValue();
                        CellStyle style = cell.getCellStyle();
                        DecimalFormat format = new DecimalFormat();
                        String temp = style.getDataFormatString();
                        // 单元格设置成常规
                        if (temp.equals("General")) {
                            format.applyPattern("#");
                        }
                        result = format.format(value);
                    }
                    break;
                default:
                    result = "";
                    break;
            }
        }
        return result.toString();
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
