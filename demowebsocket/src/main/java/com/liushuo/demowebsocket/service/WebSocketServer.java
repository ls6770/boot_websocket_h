package com.liushuo.demowebsocket.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {

    //静态变量，记录当前在线连接数，
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public  void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        System.out.println("一个客户端连接进来了 ... 它的sessionid是：" + session.getId());
//        addOnlineCount();
        faSongText();

    }

    /**
     * 设置定时器固定发送10s
     */
    public void faSongText(){
        StringBuffer str = new StringBuffer("");
        //设置定时器，间隔10s执行一次
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                long old = calendar.getTimeInMillis();
                int heartbeat= (int)(Math.random()*100);
                int maibo= (int)(Math.random()*120);
                String text="{date:"+old+",heartbeat:"+heartbeat+",maibo:"+maibo+"}";
//                if (str.length()==0 || str.equals("")){
//                    str.append(text);
//                }else{
//                    str.append(","+text);
//                }
                try {
                    sendMessage(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },2000,500);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){

    }

    /**
     * 收到客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message,Session session){

    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session,Throwable error){
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException{
        session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
//        log.info(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
