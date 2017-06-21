package webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by hq on 16/10/27.
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //注册连接点, 连接入口
        stompEndpointRegistry.addEndpoint("/coordination").withSockJS();
    }

    public void configureClientInboundChannel(ChannelRegistration channelRegistration) {

    }

    public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {

    }

    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        //消息处理
        messageBrokerRegistry.enableSimpleBroker("/userChat");
        messageBrokerRegistry.setApplicationDestinationPrefixes("/app");
    }
}
