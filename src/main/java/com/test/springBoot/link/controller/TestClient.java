package com.test.springBoot.link.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Controller
@RequestMapping("/link/client")
public class TestClient {
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void send() throws IOException {
        //创建同一连接
        Socket s = new Socket("127.0.0.1",6666);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        dos.writeUTF("nth");
    }
}
