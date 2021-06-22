package com.test.springBoot.java11;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


/**
 * @author wangyinjia
 * @description java 11 controller
 * @date 2021/6/18
 */
@Api(tags = "Java11 控制器")
@RestController
@RequestMapping("/java11")
public class Java11Controller {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 1080)))
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
                .uri(URI.create("http://127.0.0.1:8011/test/test"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // 加header
                .build();

        CompletableFuture<HttpResponse<String>> asyncResponse =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String async = asyncResponse.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        System.out.println(async);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        // print status code
        System.out.println(response.statusCode());
        // print response body
        System.out.println(response.body());


    }

}
