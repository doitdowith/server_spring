package com.seoultech.capstone.chat.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.chat.controller.dto.ChatRequest.CreateDto;
import com.seoultech.capstone.chat.repository.ChatRepository;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.Role;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.message.ChatMessage;
import com.seoultech.capstone.room.Room;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class ChatServiceTests {

  @InjectMocks
  ChatService chatService;

  @Mock
  ChatRepository chatRepository;

  @Mock
  MemberService memberService;

  Member member;
  Chat chat;
  Room room;

  @BeforeEach
  void init() {
    member = new Member("조재영", "jdyj@naver.com", Role.USER);
    room = new Room(member, "제목", "설명", "색상", 5, LocalDate.now());
    chat = new Chat("채팅 내용", member, room);
  }

  @Test
  @Transactional
  void saveTest() {
    ChatMessage create = new ChatMessage(member.getId(), "채팅내용", room.getId());

    given(memberService.findMemberById(any())).willReturn(member);
    given(chatRepository.save(any())).willReturn(chat);

    chatService.saveChat(create);
  }


}