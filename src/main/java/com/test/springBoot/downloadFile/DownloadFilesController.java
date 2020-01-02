package com.test.springBoot.downloadFile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description: 下载zip
 * 注：不要用ajax调
 * @Author: wangyinjia
 * @Date: 2019/12/27
 * @Company: kiisoo
 * @Version: 1.0
 */
@Controller
@RequestMapping("download")
public class DownloadFilesController {
    @GetMapping(value = "/zip")
    public void downloadFile(HttpServletResponse response) throws Exception {
        byte[] buffer = new byte[1024];

        response.addHeader("Content-Disposition", "attachment;filename="+new String("店铺名称".getBytes("utf-8"),"ISO8859-1")
                +System.currentTimeMillis()+ ".zip");
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());

        System.out.println("总进度：" + 1 + " | 当前进度：" + 1);

        InputStream inStream = getInputStream("http://img.kiisoo.com/" + "ea48593c114545febdc50f3531d3d6b0.jpg");
        out.putNextEntry(new ZipEntry("aaa" + ".jpg"));
        int len;
        // 读入需要下载的文件的内容，打包到zip文件
        while ((len = inStream.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.closeEntry();
        inStream.close();

        out.flush();
        out.close();
        System.out.println("生成压缩包成功");

    }
    private InputStream getInputStream(String fileUrl) {

        URLConnection connection = null;
        try {
            connection = new URL(fileUrl).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;

    }

    @GetMapping(value = "/one")
    public void downloadOneFile(HttpServletResponse response, String url) throws Exception {
        byte[] buffer = new byte[1024];
        String fileName = "文件名";
        // IE浏览器
        fileName = URLEncoder.encode(fileName, "utf-8");
        fileName = fileName.replace("+", " ");
        response.setContentType("application/x-msdownload"); // 设置内容类型为下载类型
        response.addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes(),"iso-8859-1"));
        ServletOutputStream outputStream = response.getOutputStream();
        InputStream inStream = null;
        inStream = getInputStream(url);
        int len;
        // 读入需要下载的文件的内容
        while ((len = inStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        outputStream.flush();
        outputStream.close();
    }

}
