package com.seoultech.capstone.message;

import com.seoultech.capstone.chat.service.ChatService;
import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

  private final SimpMessageSendingOperations simpMessageSendingOperations;
  private final ChatService chatService;
  private final MemberService memberService;

  @MessageMapping("/chat")
  public void enter(ChatMessage message) {
    log.info("memberId -> {}", message.getMemberId());
    Member member = memberService.findMemberById(message.getMemberId());
    ReceiveMessage receiveMessage = chatService.saveChat(member, message);
    simpMessageSendingOperations.convertAndSend("/sub/room/" + message.getRoomId(),
        receiveMessage);
  }


}
