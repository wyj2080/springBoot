package com.test.springBoot.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangyinjia
 * @Date: 2020/8/13
 * @Version: 1.0
 */
@Controller
@RequestMapping("/test")
public class FileController {
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void read() throws IOException {
        File file = new File("d://secure-20200809");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();
        String tempString = null;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
            // 显示行号
//            System.out.println(tempString);
            if(tempString.contains("Accepted password for") && !tempString.contains("192.168.0.148")){
                list.add(tempString);
            }
        }
        reader.close();
        list.forEach(t -> System.out.println(t));
    }
}
