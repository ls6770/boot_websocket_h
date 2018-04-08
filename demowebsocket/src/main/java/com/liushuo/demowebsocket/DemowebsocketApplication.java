package com.liushuo.demowebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocket
@EnableScheduling
public class DemowebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemowebsocketApplication.class, args);
	}
}
