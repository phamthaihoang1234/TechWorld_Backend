package com.example.TechWorld.configuration;

import com.example.TechWorld.handler.NotificationWebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final static String NOTIFICATION_ENDPOINT = "/notification";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getNotificationWebSocketHandler(), NOTIFICATION_ENDPOINT);
    }

    @Bean
    public WebSocketHandler getNotificationWebSocketHandler() {
        return new NotificationWebSocket();
    }
}
