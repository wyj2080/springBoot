package com.test.springBoot.excel.controller;

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
        OutputStream output = response.getOutputStream();
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=accountCount"
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
