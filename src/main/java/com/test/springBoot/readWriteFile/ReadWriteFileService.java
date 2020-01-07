package com.test.springBoot.readWriteFile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @Description: 读写文件service
 * @Author: wangyinjia
 * @Date: 2020/1/7
 * @Version: 1.0
 */
@Slf4j
@Service
public class ReadWriteFileService {
    /**
     * 读文件
     * @param fileName 文件路径
     * @return 内容string
     * @throws Exception 异常
     */
    public String readFile(String fileName) {
        String content = "";
        try {
            File jsonFile = new File(fileName);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            content = sb.toString();
        }catch (Exception e){
            log.error("error", e);
        }
        return content;
    }

    /**
     * 写文件
     * @param fileName 文件路径
     * @param content 内容
     * @throws Exception 异常
     */
    public void writeFile(String fileName, String content) {
        try {
            FileWriter fw = new FileWriter(new File(fileName));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
