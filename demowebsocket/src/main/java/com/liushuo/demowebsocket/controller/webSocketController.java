package com.liushuo.demowebsocket.controller;

import com.liushuo.demowebsocket.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

@Controller
@RequestMapping(value = "/socket")
public class webSocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping(value="/index")
    public String index(){
        return "/index/demo";
    }

    @RequestMapping(value = "/web")
    public String pushToWeb(){
        //设置数据
        //数据格式样式 inputData={date:now,heartbeat:70,maibo:100}
        return "/index/demo";
    }


}
