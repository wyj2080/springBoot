package com.test.springBoot.readWriteFile;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写文件
 * @Author: wangyinjia
 * @Date: 2020/1/6
 * @Version: 1.0
 */
@Slf4j
public class readWriteFileController {
    public static void main(String[] args) {
        String fileName = "d:\\test.txt";
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        list.parallelStream().forEach(a -> {
            String content = readFile(fileName);
            System.out.println("read:" + content);
            content = Integer.valueOf(content) + 1 + "";
            writeFile(fileName, content);
            System.out.println("write:" + content);
        });

    }

    /**
     * 读文件
     * @param fileName 文件路径
     * @return 内容string
     * @throws Exception 异常
     */
    public static String readFile(String fileName) {
        String content = "";
        File jsonFile = new File(fileName);
        ReadWriteLock rwl = new ReentrantReadWriteLock();
        rwl.writeLock().lock();
        try {
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            content = sb.toString();
        }catch (Exception e){
            log.error("",e);
        }
        rwl.writeLock().unlock();
        return content;
    }

    /**
     * 写文件
     * @param fileName 文件路径
     * @param content 内容
     * @throws Exception 异常
     */
    public static void writeFile(String fileName, String content) {
        ReadWriteLock rwl = new ReentrantReadWriteLock();
        rwl.writeLock().lock();
        try {
            FileWriter fw = new FileWriter(new File(fileName));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
        }catch (Exception e){
            System.out.println(e);
        }
        rwl.writeLock().unlock();
    }
}
