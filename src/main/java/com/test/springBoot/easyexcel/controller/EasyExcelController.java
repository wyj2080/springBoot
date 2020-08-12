package com.test.springBoot.easyexcel.controller;

import com.test.springBoot.easyexcel.service.EasyExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wangyinjia
 * @description
 * @date 2020/8/12
 */
@Controller
@RequestMapping("/easy/excel")
public class EasyExcelController {
    @Autowired
    private EasyExcelService easyExcelService;
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read(){
        easyExcelService.simpleRead();
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public void write(){
        easyExcelService.simpleWrite();
    }
}
