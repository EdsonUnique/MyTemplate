package edson.MyTemplate.utils.EasyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import edson.MyTemplate.Entity.User;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * EasyExcel框架工具类   版本3.0.5
 *
 * @Author: yangxi
 * @Date: 2021/12/8 10:03
 */
public class EasyExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(EasyExcelUtil.class);

    /**
     * @param inputStream 需要读取的excel文件流
     * @param clazz       数据保存的class
     * @return 数据对象List
     * @description: 从Excel读取数据  默认只读取第一个sheet 文件流会自动关闭
     */
    public static List readExcel(InputStream inputStream, Class clazz) {

        List dataList = new ArrayList<>();

        if (null != inputStream) {
            EasyExcel.read(inputStream, clazz, new AnalysisEventListener<Object>() {

                @Override
                public void onException(Exception exception, AnalysisContext context) throws Exception {
                    //处理异常
                    // 如果是某一个单元格的转换异常 能获取到具体行号
                    // 如果要获取头的信息 配合invokeHeadMap使用
                    if (exception instanceof ExcelDataConvertException) {
                        ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
                        logger.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                                excelDataConvertException.getColumnIndex());
                    }
                    throw new Exception(exception);//抛出异常  不继续解析下一行
                }

                @Override
                public void invoke(Object t, AnalysisContext analysisContext) {
                    //读取每一行数据
                    dataList.add(t);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    //读取数据结束
                    logger.info("---excel文件读取结束---");
                }
            }).sheet().doRead();
        }

        return dataList;

    }

    /**
     * @param response
     * @param dataList  需要导出的数据
     * @param filename  导出的excel文件名  例如 template.xlsx
     * @param sheetName sheet页名称
     * @param excelType excel版本  xls或 xlsx
     * @description:导出Excel 只导出在第一个sheet
     */
    public static void writeExcel(HttpServletResponse response, List<?> dataList, Set<String> columnSet, String filename, String sheetName, ExcelTypeEnum excelType) throws Exception {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码
            String fileName = URLEncoder.encode(filename, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);

            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), User.class)
                    .excelType(excelType)
                    .registerWriteHandler(setExcelStyle())
                    .registerWriteHandler(new CustomCellWriteWeightConfig())
                    .autoCloseStream(Boolean.FALSE)
                    .includeColumnFiledNames(columnSet)//指定导出的字段  列
                    .sheet(sheetName)
                    .doWrite(dataList);
        } catch (Exception e) {
            logger.error("excel文件导出失败！");
            throw new Exception(e);
        }
    }

    //设置导出excel样式
    private static HorizontalCellStyleStrategy setExcelStyle() {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 14);
        headWriteFont.setFontName("宋体");
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setWrapped(false);//标题不换行
//            headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
//            contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景色
//            contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 14);
        contentWriteFont.setFontName("宋体");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        return horizontalCellStyleStrategy;
    }
}
