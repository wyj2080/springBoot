package com.test.springBoot.excel.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 导出excel
 * @Author: wangyinjia
 * @Date: 2019/11/22
 * @Version: 1.0
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 导出excel
     * @param response http响应
     */
    @RequestMapping(value = "/output", method = RequestMethod.GET)
    public HttpServletResponse output(HttpServletResponse response) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        //工作表名称
        XSSFSheet sheetA = wb.createSheet("表A");
        XSSFSheet sheetB = wb.createSheet("表B");
        //单元格合并，参数：开始行，结束行，开始列，结束列
        sheetA.addMergedRegion(new CellRangeAddress(0,0,0,2));
        //第一行,合并后设值第一个格子有用，第二个标题要第四列
        Row row1 = sheetA.createRow(0);
        Cell cell1 = row1.createCell(0);
        Cell cell4 = row1.createCell(3);
        cell1.setCellValue("大标题");
        cell4.setCellValue("标题4");
        //第二行
        Row row2 = sheetA.createRow(1);
        Cell cell21 = row2.createCell(0);
        Cell cell22 = row2.createCell(1);
        cell21.setCellValue("第二行第1列");
        cell22.setCellValue("第二行第2列");
        OutputStream output = response.getOutputStream();
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=excelName"
                +System.currentTimeMillis()+ ".xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        wb.write(output);
        return response;
    }

    /**
     * 导入excel
     * @return
     */
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public Map<String, Object> input() {

        return new HashMap<>();
    }
}
