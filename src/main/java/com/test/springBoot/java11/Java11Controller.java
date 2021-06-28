package com.test.springBoot.java11;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


/**
 * @author wangyinjia
 * @description java 11 controller
 * @date 2021/6/18
 */
@Api(tags = "Java11 控制器")
@RestController
@RequestMapping("/java11")
public class Java11Controller {
    //并发client
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            //线程池
            .executor(executorService)
            //认证
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            "user",
                            "password".toCharArray());
                }
            })
            //代理
//            .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 1080)))
            //超时
            .connectTimeout(Duration.ofSeconds(5)).build();

    @GetMapping("/var")
    public String findList(){
        var a = "123a";
        return a;
    }

    @GetMapping("/httpClient2")
    public void httpClient2() throws Exception {

        //GET 请求
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://127.0.0.1:8011/test/test?userId=ccc"))
                .setHeader("token", "123456")
                .build();

//        CompletableFuture<HttpResponse<String>> asyncResponse =
//                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//        String async = asyncResponse.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
//        System.out.println(async);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        // print status code
        System.out.println(response.statusCode());
        // print response body
        System.out.println(response.body());


    }

    @GetMapping("/post")
    public void httpClient2Post() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeId","102");
        jsonObject.put("employ","10201");
        //GET 请求
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString()))
                .uri(URI.create("http://127.0.0.1:8011/test/post/08902"))
                .setHeader("token", "123456")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        // print status code
        System.out.println(response.statusCode());
        // print response body
        JSONObject result = JSON.parseObject(response.body(), JSONObject.class);
        System.out.println(result.toJSONString());
    }

    @GetMapping("/api")
    public void api() throws Exception {
        //--------------------String
        //分割行
        String a = "aaa\n123";
        a.lines().forEach(System.out::println);
        a.lines().count();
        //strip 去除空格：两边，左侧，右侧
        String b = "  aa bb  ";
        System.out.println(b.strip());
        System.out.println(b.stripLeading());
        System.out.println(b.stripTrailing());
        //--------------------collection
        //list与arr互相转换
        List<Integer> list = Arrays.asList(2,3,1,null,4,0);
        Integer[] intArr = list.toArray(Integer[]::new);
        //文件(需要本身utf-8编码，不然报错)，读、写、删文件
        Path path = Paths.get("D://vue.txt");
        Files.readString(path);
        Path writePath = Paths.get("D://write.txt");
        Files.writeString(writePath, "write thing");
        Files.delete(writePath);

        list = list.stream().filter((@NotNull var t) -> t>1).collect(Collectors.toList());
        System.out.println(list.toString());


    }

}
