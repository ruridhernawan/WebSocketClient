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
        try {
            // Send message to WebSocket server
            String message = """
                    {"user" : "live_agratek"}
                     """;
            webSocketHandler.sendMessage(message);
            System.out.println("--------------->>>>>>>>>>>>>>>>>>> websocket connect");
            sshLogMonitorService.startMonitoring();
            System.out.println("Start Monitoring log Sucess");
            return "Message sent to WebSocket server";
        }
        catch (Exception e) {
            return "Server Contoller is useless";
        }
    }
}
