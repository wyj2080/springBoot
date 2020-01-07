package com.test.springBoot.readWriteFile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 读写文件
 * @Author: wangyinjia
 * @Date: 2020/1/6
 * @Version: 1.0
 */
@Slf4j
@Controller
@RequestMapping("rw")
public class readWriteFileController {
    /**
     * 读写文件service
     */
    @Autowired
    private ReadWriteFileService readWriteFileService;

    @GetMapping(value = "/file")
    public void readAndWriteFile() {
        String fileName = "d:\\test.txt";
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        String lock = "lock";
        list.parallelStream().forEach(a -> {
            //读写原子操作
            synchronized (lock){
                String content = readWriteFileService.readFile(fileName);
                System.out.println("read:" + content);
                content = Integer.valueOf(content) + 1 + "";
                readWriteFileService.writeFile(fileName, content);
                System.out.println("write:" + content);
            }
        });

    }

}
