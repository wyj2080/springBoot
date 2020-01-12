package com.test.springBoot.cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Description 执行cmd指令
 * @Author wyj2080
 * @Version V1.0.0
 * @Date 2020/1/12
 */
public class cmdMain {
    public static void main(String[] args){
        try {
            String cmd = "ipconfig";
            String[] cmdA = new String[]{"/bin/sh", "-c", cmd};
            Process ps = Runtime.getRuntime().exec(cmdA);
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(), "GBK"));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
