package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by hq on 16/11/16.
 */
@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping(value = "/userChat")
    public void userChat() {
        String dest = "userChat/chat";
        template.convertAndSend(dest, "Hello");
    }

}
