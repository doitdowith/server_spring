package com.seoultech.capstone.message;

import com.seoultech.capstone.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

  private final SimpMessageSendingOperations simpMessageSendingOperations;
  private final ChatService chatService;

  @MessageMapping("/chat")
  public void enter(ChatMessage message) {
    ReceiveMessage receiveMessage = chatService.saveChat(message);
    simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getRoomId(),
        receiveMessage);
  }


}
