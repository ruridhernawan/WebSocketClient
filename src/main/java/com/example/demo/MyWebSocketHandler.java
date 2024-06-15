package com.example.demo;

import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class MyWebSocketHandler extends AbstractWebSocketHandler {
	public WebSocketSession session;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Handle incoming text message
        System.out.println("Received message: " + message.getPayload() + " " + session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Connection established, perform initialization
    	VariableStatic.session = session ;
    	this.session = session;
        System.out.println("Connected to WebSocket server " + this.session);
        String url = "http://localhost:8081/send-message";
        RestTemplate restTemplate = new RestTemplate();
        String message = restTemplate.getForObject(url, String.class);
        
       
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        // Handle transport error
        System.err.println("Error in WebSocket transport: " + exception.getMessage());
    }
    
    public void sendMessage(String message) {
    	this.session = VariableStatic.session ;
    	System.out.println("Try Message sent1: " + message + " " + this.session);
        if (this.session != null ) {
        	System.out.println("Try Message sent2: " + message);
            try {
                this.session.sendMessage(new TextMessage(message));
                System.out.println("Message sent: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("WebSocket session is not open.");
        }
    }
}

