package com.seoultech.capstone.chat.controller;

import com.seoultech.capstone.chat.dto.ChatListAllResponse;
import com.seoultech.capstone.chat.service.ChatService;
import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
public class ChatController {

  private final ChatService chatService;
  private final MemberService memberService;

  @GetMapping("/{roomId}")
  public ResponseEntity<ChatListAllResponse> chatListAll(@Auth String memberId,
      @PathVariable String roomId) {
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(chatService.listAll(member, roomId));
  }


}
