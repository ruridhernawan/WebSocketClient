package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class WebSocketController {

    private final MyWebSocketHandler webSocketHandler;
    @Autowired
    private SSHLogMonitorService sshLogMonitorService;

    public WebSocketController(MyWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @GetMapping("/send-message")
    public String sendMessageToWebSocket() {
        // Send message to WebSocket server
        webSocketHandler.sendMessage("TERKIRIM");
        sshLogMonitorService.startMonitoring();
        return "Message sent to WebSocket server";
    }
}
