//package edson.MyTemplate.utils;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * maven配置：
// * <!--        excel表导出-->
// *         <dependency>
// *             <groupId>org.apache.poi</groupId>
// *             <artifactId>poi</artifactId>
// *             <version>3.15</version>
// *         </dependency>
// */
//public class ExportExcelUtil {
//
//    //显示的导出表1的标题
//    private String sheet1Name;
//    //显示的导出表2的标题
//    private String sheet2Name;
//    //导出表的列名
//    private String[] rowName1 ;
//    //导出表2的列名
//    private String[] rowName2 ;
//
//    private List<Object[]>  dataList1 = new ArrayList<Object[]>();
//    private List<Object[]>  dataList2 = new ArrayList<Object[]>();
//
//    HttpServletResponse response;
//
//
//    //构造方法，传入要导出的数据
//    public ExportExcelUtil(String sheet1Name, String sheet2Name, String[] rowName1, String[] rowName2, List<Object[]> dataList1, List<Object[]> dataList2){
//        this.dataList1 = dataList1;
//        this.dataList2 = dataList2;
//        this.rowName1 = rowName1;
//        this.rowName2 = rowName2;
//        this.sheet1Name = sheet1Name;
//        this.sheet2Name = sheet2Name;
//    }
//
//    /*
//     * 导出数据
//     * */
//    public void export(OutputStream out) throws Exception{
//
//        try{
//            HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象
//            HSSFSheet sheet1 = workbook.createSheet(sheet1Name);                  // 创建工作表
//            HSSFSheet sheet2 = workbook.createSheet(sheet2Name);
//
//            sheet1=writeSheet(workbook,sheet1,rowName1,dataList1);
//            sheet2=writeSheet(workbook,sheet2,rowName2,dataList2);
//
//            if(workbook !=null){
//                try{
//
//
//                    workbook.write(out);
//
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        finally{
//
//            out.close();
//        }
//    }
//
//    public HSSFSheet writeSheet(HSSFWorkbook workbook, HSSFSheet sheet, String[] rowName, List<Object[]> dataList){
//        // 产生表格标题行
//        //          HSSFRow rowm = sheet.createRow(0);
//        //          HSSFCell cellTiltle = rowm.createCell(0);
//
//        //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
//        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
//        HSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象
//
//        //          sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));//合并单元格
//        //          cellTiltle.setCellStyle(columnTopStyle);
//        //          cellTiltle.setCellValue(title);
//
//        // 定义所需列数
//        int columnNum = rowName.length;
//        HSSFRow rowRowName = sheet.createRow(0);                // 在索引2的位置创建行(最顶端的行开始的第二行)
//
//        // 将列头设置到sheet的单元格中
//        for(int n=0;n<columnNum;n++){
//            HSSFCell cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格
//            cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
//            HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
//            cellRowName.setCellValue(text);                                 //设置列头单元格的值
//            cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样式
//        }
//
//        //将查询出的数据设置到sheet对应的单元格中
//        for(int i=0;i<dataList.size();i++){
//            Object[] obj = dataList.get(i);//遍历每个对象
//            HSSFRow row = sheet.createRow(i+1);//创建所需的行数（从第二行开始写数据）
//
//            for(int j=0; j<obj.length; j++){
//                HSSFCell cell = null;   //设置单元格的数据类型
//                if(j == 0){
//                    cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
//                    cell.setCellValue(i+1);
//                }else{
//                    cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
//                    if(!"".equals(obj[j]) && obj[j] != null){
//                        cell.setCellValue(obj[j].toString());                       //设置单元格的值
//                    }
//                }
//                cell.setCellStyle(style);                                   //设置单元格样式
//            }
//        }
//        //让列宽随着导出的列长自动适应
//        for (int colNum = 0; colNum < columnNum; colNum++) {
//            int columnWidth = sheet.getColumnWidth(colNum) / 256;
//            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
//                HSSFRow currentRow;
//                //当前行未被使用过
//                if (sheet.getRow(rowNum) == null) {
//                    currentRow = sheet.createRow(rowNum);
//                } else {
//                    currentRow = sheet.getRow(rowNum);
//                }
//                //                 if (currentRow.getCell(colNum) != null) {
//                //                     HSSFCell currentCell = currentRow.getCell(colNum);
//                //                      if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                //                          int length =     currentCell.getStringCellValue().getBytes().length;
//                //                          if (columnWidth < length) {
//                //                              columnWidth = length;
//                //                          }
//                //                      }
//                //                  }
//                if (currentRow.getCell(colNum) != null) {
//                    HSSFCell currentCell = currentRow.getCell(colNum);
//                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                        int length = 0;
//                        try {
//                            length = currentCell.getStringCellValue().getBytes().length;
//                        } catch (Exception e) {
//                            //e.printStackTrace();
//                        }
//                        if (columnWidth < length) {
//                            columnWidth = length;
//                        }
//                    }
//                }
//
//            }
//            if(colNum == 0){
//                sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
//            }else{
//                sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
//            }
//        }
//
//        return sheet;
//    }
//
//    /*
//     * 列头单元格样式
//     */
//    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
//
//        // 设置字体
//        HSSFFont font = workbook.createFont();
//        //设置字体大小
//        font.setFontHeightInPoints((short)11);
//        //字体加粗
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        //设置字体名字
//        font.setFontName("Courier New");
//        //设置样式;
//        HSSFCellStyle style = workbook.createCellStyle();
//        //设置底边框;
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        //设置底边框颜色;
//        style.setBottomBorderColor(HSSFColor.BLACK.index);
//        //设置左边框;
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        //设置左边框颜色;
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//        //设置右边框;
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        //设置右边框颜色;
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//        //设置顶边框;
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        //设置顶边框颜色;
//        style.setTopBorderColor(HSSFColor.BLACK.index);
//        //在样式用应用设置的字体;
//        style.setFont(font);
//        //设置自动换行;
//        style.setWrapText(false);
//        //设置水平对齐的样式为居中对齐;
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        //设置垂直对齐的样式为居中对齐;
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//        return style;
//
//    }
//
//    /*
//     * 列数据信息单元格样式
//     */
//    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
//        // 设置字体
//        HSSFFont font = workbook.createFont();
//        //设置字体大小
//        //font.setFontHeightInPoints((short)10);
//        //字体加粗
//        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        //设置字体名字
//        font.setFontName("Courier New");
//        //设置样式;
//        HSSFCellStyle style = workbook.createCellStyle();
//        //设置底边框;
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        //设置底边框颜色;
//        style.setBottomBorderColor(HSSFColor.BLACK.index);
//        //设置左边框;
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        //设置左边框颜色;
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//        //设置右边框;
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        //设置右边框颜色;
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//        //设置顶边框;
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        //设置顶边框颜色;
//        style.setTopBorderColor(HSSFColor.BLACK.index);
//        //在样式用应用设置的字体;
//        style.setFont(font);
//        //设置自动换行;
//        style.setWrapText(false);
//        //设置水平对齐的样式为居中对齐;
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        //设置垂直对齐的样式为居中对齐;
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//        return style;
//
//    }
//
//
//
//}
///*  逻辑代码调用
// 仓库导出
//
//@GetMapping("/exportExcel")
//public void exportExcel(HttpServletResponse response) throws ProjectException{
//
//
//      根据用户部门导出仓库
//
//    LoginUserModel loginUserModel=loginUser.getCurrentLoginUser(null);
//    List<TransactionModel>transactionModelList=transactionService.fetchGoodsExcel(loginUserModel.getDepartment().getId(), GoodsStatusEnum.STOCKIN.getId());
//
//    List<TransactionModel>transactionModelList2=transactionService.fetchGoodsExcel(loginUserModel.getDepartment().getId(), GoodsStatusEnum.TEMP_STOCKOUT.getId());
//
//
//    //导出文件的标题
//    String title = "盘库导出"+DateFormatUtil.getDateStr(LocalDateTime.now(),"yyyyMMddHHmmss") +".xls";
//    //设置表格标题行
//    String[] headers = new String[] {"序号","货物条码","货物名称","货物分类1","货物分类2"
//            ,"所属业务现场","入库时间","货物所属仓库(1-10)","货物位置区域(a-z)"
//            ,"货物位置(1-20)","邮包单号","包裹数量","外观颜色","重量(单位kg)","国家"
//            ,"寄件人","收件人","收件人地址","联系人","联系电话","入库交与人","货物状态"
//            ,"货物备注","最后一次入库申请操作人姓名","最后一次入库申请操作人编号"
//            ,"最后一次入库申请时间","最后一次入库申请备注","最后一次入库审核人姓名"
//            ,"最后一次入库审核人编号","最后一次入库审核时间","最后一次入库审核备注"};
//    List<Object[]> dataList = new ArrayList<Object[]>();
//    Object[] objs = null;
//    for (int i = 0; i < transactionModelList.size(); i++) {
//        objs = new Object[headers.length];
//        objs[0] = i+1;//设置序号,在工具类中会出现
//        objs[1] = transactionModelList.get(i).getSn();
//        objs[2] = transactionModelList.get(i).getGoodsName();
//        objs[3] = transactionModelList.get(i).getParamCategory1GoodsName();
//        objs[4] = transactionModelList.get(i).getParamCategory2GoodsName();
//        objs[5] = transactionModelList.get(i).getDepartmentName();
//        objs[6] = transactionModelList.get(i).getStockInDate();
//        objs[7] = transactionModelList.get(i).getGoodsPositionStore();
//        objs[8] = transactionModelList.get(i).getGoodsPositionArea();
//
//        objs[9] = transactionModelList.get(i).getGoodsPositionNum();
//        objs[10] = transactionModelList.get(i).getPostalSn();
//        objs[11] = transactionModelList.get(i).getPostalNum();
//        objs[12] = transactionModelList.get(i).getAppearanceColor();
//        objs[13] = transactionModelList.get(i).getWeight();
//        objs[14] = transactionModelList.get(i).getParamCountryName();
//        objs[15] = transactionModelList.get(i).getPostalSender();
//
//        objs[16] = transactionModelList.get(i).getPostalReceiver();
//        objs[17] = transactionModelList.get(i).getPostalReceiverAddress();
//        objs[18] = transactionModelList.get(i).getConnectPerson();
//        objs[19] = transactionModelList.get(i).getConnectPhone();
//        objs[20] = transactionModelList.get(i).getStockinReceiver();
//        objs[21] = transactionModelList.get(i).getGoodsStatusName();
//        objs[22] = transactionModelList.get(i).getSummary();
//        objs[23] = transactionModelList.get(i).getStockinApplyPersonName();
//        objs[24] = transactionModelList.get(i).getStockinApplyPersonCode();
//        objs[25] = DateFormatUtil.getDateStr(transactionModelList.get(i).getStockinApplyTime());
//        objs[26] = transactionModelList.get(i).getStockinApplySummary();
//        objs[27] = transactionModelList.get(i).getStockinAuditPersonName();
//        objs[28] = transactionModelList.get(i).getStockinAuditPersonCode();
//        objs[29] = DateFormatUtil.getDateStr(transactionModelList.get(i).getStockinAuditTime());
//        objs[30] = transactionModelList.get(i).getStockinAuditSummary();
//        dataList.add(objs);//数据添加到excel表格
//    }
//
//
//    //设置表格标题行
//    String[] headers2 = new String[] {"序号","货物条码","货物名称","货物分类1","货物分类2"
//            ,"所属业务现场","入库时间","货物所属仓库(1-10)","货物位置区域(a-z)"
//            ,"货物位置(1-20)","邮包单号","包裹数量","外观颜色","重量(单位kg)","国家"
//            ,"寄件人","收件人","收件人地址","联系人","联系电话","暂时出库接收人","货物状态"
//            ,"货物备注","最后一次暂时出库申请操作人姓名","最后一次暂时出库申请操作人编号"
//            ,"最后一次暂时出库申请时间","最后一次暂时出库申请备注","最后一次暂时出库审核人姓名"
//            ,"最后一次暂时出库审核人编号","最后一次暂时出库审核时间","最后一次暂时出库审核备注"};
//    List<Object[]> dataList2 = new ArrayList<Object[]>();
//    Object[] objs2 = null;
//    for (int i = 0; i < transactionModelList2.size(); i++) {
//        objs2 = new Object[headers2.length];
//        objs2[0] = i+1;//设置序号,在工具类中会出现
//        objs2[1] = transactionModelList2.get(i).getSn();
//        objs2[2] = transactionModelList2.get(i).getGoodsName();
//        objs2[3] = transactionModelList2.get(i).getParamCategory1GoodsName();
//        objs2[4] = transactionModelList2.get(i).getParamCategory2GoodsName();
//        objs2[5] = transactionModelList2.get(i).getDepartmentName();
//        objs2[6] = transactionModelList2.get(i).getStockInDate();
//        objs2[7] = transactionModelList2.get(i).getGoodsPositionStore();
//        objs2[8] = transactionModelList2.get(i).getGoodsPositionArea();
//
//        objs2[9] = transactionModelList2.get(i).getGoodsPositionNum();
//        objs2[10] = transactionModelList2.get(i).getPostalSn();
//        objs2[11] = transactionModelList2.get(i).getPostalNum();
//        objs2[12] = transactionModelList2.get(i).getAppearanceColor();
//        objs2[13] = transactionModelList2.get(i).getWeight();
//        objs2[14] = transactionModelList2.get(i).getParamCountryName();
//        objs2[15] = transactionModelList2.get(i).getPostalSender();
//
//        objs2[16] = transactionModelList2.get(i).getPostalReceiver();
//        objs2[17] = transactionModelList2.get(i).getPostalReceiverAddress();
//        objs2[18] = transactionModelList2.get(i).getConnectPerson();
//        objs2[19] = transactionModelList2.get(i).getConnectPhone();
//        objs2[20] = transactionModelList2.get(i).getTempStockoutReceiver();
//        objs2[21] = transactionModelList2.get(i).getGoodsStatusName();
//        objs2[22] = transactionModelList2.get(i).getSummary();
//        objs2[23] = transactionModelList2.get(i).getTempStockoutApplyPersonName();
//        objs2[24] = transactionModelList2.get(i).getTempStockoutApplyPersonCode();
//        objs2[25] = DateFormatUtil.getDateStr(transactionModelList2.get(i).getTempStockoutApplyTime());
//        objs2[26] = transactionModelList2.get(i).getTempStockoutApplySummary();
//        objs2[27] = transactionModelList2.get(i).getTempStockoutAuditPersonName();
//        objs2[28] = transactionModelList2.get(i).getTempStockoutAuditPersonCode();
//        objs2[29] = DateFormatUtil.getDateStr(transactionModelList2.get(i).getTempStockoutAuditTime());
//        objs2[30] = transactionModelList2.get(i).getTempStockoutAuditSummary();
//        dataList2.add(objs2);//数据添加到excel表格
//    }
//
//    //使用流将数据导出
//    OutputStream out = null;
//    try {
//        //防止中文乱码
//        String headStr = "attachment;filename=\"" + new String( title.getBytes("gbk"), "iso8859-1" ) + "\"";
//        response.reset();
//        response.setHeader("Content-disposition", headStr);
//        response.setContentType("application/x-download");
//        out = response.getOutputStream();
//        ExportExcelUtil ex = new ExportExcelUtil("已入库货物清单","已暂时出库货物清单", headers,headers2, dataList,dataList2);
//        ex.export(out);
//
//        out.flush();
//        out.close();
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
//
//
//
//
// */