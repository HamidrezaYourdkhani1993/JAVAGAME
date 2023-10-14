package com.myjavagame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket configuration class for configuring STOMP WebSocket endpoints and message brokers.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * Register STOMP endpoints, with SockJS fallback.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/gameplay").withSockJS();
    }

    /**
     * Configure message broker options.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        // Set the application destination prefix for messages handled by the application
        messageBrokerRegistry.setApplicationDestinationPrefixes("/app");
        
        // Enable a simple in-memory message broker to deliver messages to clients with "/topic" prefix
        messageBrokerRegistry.enableSimpleBroker("/topic");
    }
}
