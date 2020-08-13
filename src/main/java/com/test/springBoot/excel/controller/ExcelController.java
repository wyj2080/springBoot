//package com.test.springBoot.excel.controller;
//
//import com.test.springBoot.mysql.entity.AccountDO;
//import com.test.springBoot.mysql.mapper.LinkDOMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicReference;
//
///**
// * @Description: 导出excel
// * @Author: wangyinjia
// * @Date: 2019/11/22
// * @Version: 1.0
// */
//@Slf4j
//@Controller
//@RequestMapping("/excel")
//public class ExcelController {
//    private final int WORDS_STYLE_TITLE = 1;// 标题
//    private final int WORDS_STYLE_HEAD = 2;// 列名
//    private final int WORDS_STYLE_CONTENT = 3;// 内容
//
//    @Autowired
//    LinkDOMapper linkDOMapper;
//    /**
//     * 导出excel
//     * 注：ajax调没用的，前台window.open("url")
//     * @param response http响应
//     */
//    @RequestMapping(value = "/output", method = RequestMethod.GET)
//    public HttpServletResponse output(HttpServletResponse response) throws IOException {
//        //创建工作簿，表
//        XSSFWorkbook wb = new XSSFWorkbook();
//        XSSFSheet sheetA = wb.createSheet("表A");
//        //数据list
//        List<AccountDO> list = linkDOMapper.selectList(null);
//        //生成excel，(内含标题，表头，合并，数据4个方法)
//        generateWorkBook(wb, sheetA, list);
//        //输出excel
//        OutputStream output = response.getOutputStream();
//        response.reset();
//        response.addHeader("Content-Disposition", "attachment;filename=excelName"
//                +System.currentTimeMillis()+ ".xlsx");
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        wb.write(output);
//        return response;
//    }
//
//    private void generateWorkBook(XSSFWorkbook wb, XSSFSheet sheet, List<AccountDO> list){
//        XSSFRow row = sheet.createRow(0);
//        for(int i=0;i<3;i++){
//            XSSFCell cell = row.createCell(i);
//            cell.setCellValue("第一行合并3个");
//        }
//        //大标题
//        generateFirstRow(wb, sheet, "大标题");
//        //小标题
//        generateSecondRow(wb, sheet, null);
//        //数据
//        putData(wb, sheet, list);
//    }
//
//    /**
//     * 生成第一行（标题）
//     *
//     * @param workbook 代表一个excel表格
//     * @param sheet    一个工作表
//     * @param name     名字
//     */
//    private void generateFirstRow(XSSFWorkbook workbook, XSSFSheet sheet, String name) {
//        XSSFCellStyle style = getCellStyle(workbook, WORDS_STYLE_TITLE);// 选择标题的样式
//        Row row = sheet.createRow(0);// 创建一个格子
//        row.setHeightInPoints(25);// 行高
//        Cell cell = row.createCell(0);
//        cell.setCellValue(name);
//        cell.setCellStyle(style);
//        CellRangeAddress regionHead = new CellRangeAddress(0, 0, 0, 77);// 合并单元格(首行，尾行，首列，尾列)
//        sheet.addMergedRegion(regionHead);
//    }
//
//    /**
//     * 生成第二行（表头）
//     *
//     * @param workbook 代表一个excel表格
//     * @param sheet    一个工作表
//     * @param type 不同的小标题
//     */
//    private void generateSecondRow(XSSFWorkbook workbook, XSSFSheet sheet, Integer type) {
//        // 列名
//        String[] shopRow = {"账号id", "账号名称"};
//        String[] secondRow = {};
//        secondRow = shopRow;
//        XSSFCellStyle style = getCellStyle(workbook, WORDS_STYLE_HEAD);// 粗体，居中
//        Row row = sheet.createRow(1);
//
//        // 逐个将列名放入第一行
//        for (int i = 0; i < secondRow.length; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(secondRow[i]);
//            cell.setCellStyle(style);
//        }
//    }
//
//    /**
//     * 放入数据
//     * @param workbook 工作簿
//     * @param sheet 表
//     * @param list 数据
//     */
//    private void putData(XSSFWorkbook workbook, XSSFSheet sheet, List<AccountDO> list) {
//        // 列在实体中的属性
//        List<String> fieldColumns = Arrays.asList("id", "name");
//        if(list == null){
//            return;
//        }
//        XSSFCellStyle style = getCellStyle(workbook, WORDS_STYLE_CONTENT);// 粗体，居中
//        AtomicReference<Integer> rowNum = new AtomicReference<>(2);
//        //数据List
//        list.forEach(accountDO -> {
//            int num = rowNum.getAndSet(rowNum.get() + 1);
//            Row row = sheet.createRow(num);
//            AtomicReference<Integer> cellNum = new AtomicReference<>(0);
//            //列list
//            fieldColumns.forEach(columnName -> {
//                try{
//                    String value = BeanUtils.getProperty(accountDO, columnName);
//                    Cell cell = row.createCell(cellNum.get());
//                    setCellValue(cell, value);
//                    cell.setCellStyle(style);
//                    cellNum.getAndSet(cellNum.get() + 1);
//                }catch (Exception e){
//                    log.error("BeanUtils获取列属性出错",e);
//                }
//
//            });
//        });
//    }
//
//    /**
//     * 设置列值
//     * @param cell 列
//     * @param object 值
//     */
//    private void setCellValue(Cell cell, Object object) {
//        if (object == null) {
//            cell.setCellValue("");
//        } else {
//            // 目标实体中只有整型和字符串类型
//            if (object instanceof String) {
//                cell.setCellValue(String.valueOf(object));
//            } else if (object instanceof Integer) {
//                Integer temp = (Integer) object;
//                cell.setCellValue(temp);
//            } else {
//                cell.setCellValue("");
//            }
//        }
//    }
//
//    /**
//     * 构建样式（标题、表格头、一般数据）
//     *
//     * @param workbook    代表一个excel表格
//     * @param chooseStyle 选择一个样式
//     * @return HSSFCellStyle样式
//     */
//    private XSSFCellStyle getCellStyle(XSSFWorkbook workbook, int chooseStyle) {
//        XSSFCellStyle style = workbook.createCellStyle();
//        // 设置边框
//        style.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
//        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
//        style.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
//        style.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
//        style.setLocked(false);// 设置excel是否为 只读
//
//        switch (chooseStyle) {
//            // 标题
//            case WORDS_STYLE_TITLE: {
//                style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//                //style.setIndention((short)-65);
//                XSSFFont font = workbook.createFont();
////                font.setColor();
//                font.setFontHeightInPoints((short) 20);
//                style.setFont(font);
//            }
//            break;
//            // 表头
//            case WORDS_STYLE_HEAD: {
//                style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体居中
//                XSSFFont font = workbook.createFont();
////                font.setColor(HSSFColor.BLACK.index);
//                font.setFontHeightInPoints((short) 13);
//                font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体
//                style.setFont(font);
//
//            }
//            break;
//            // 内容
//            case WORDS_STYLE_CONTENT: {
//                style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置字体居中
//                style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
//                XSSFFont font = workbook.createFont();
////                font.setColor(HSSFColor.BLACK.index);
//                font.setFontHeightInPoints((short) 12);
//                style.setFont(font);
//            }
//        }
//        return style;
//    }
//
//    /**
//     * 导入excel
//     * @return
//     */
//    @RequestMapping(value = "/input", method = RequestMethod.GET)
//    public Map<String, Object> input() {
//
//        return new HashMap<>();
//    }
//}
