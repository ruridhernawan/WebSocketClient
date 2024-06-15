package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Configuration
public class WebSocketClientConfig {

    @Bean
    public WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public AbstractWebSocketHandler handler() {
        return new MyWebSocketHandler();
    }

    @Bean
    public WebSocketConnectionManager connectionManager(WebSocketClient webSocketClient, AbstractWebSocketHandler handler) {
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(webSocketClient, handler, "ws://localhost:8082/websocket");
        connectionManager.setAutoStartup(true);
        return connectionManager;
    }
}

