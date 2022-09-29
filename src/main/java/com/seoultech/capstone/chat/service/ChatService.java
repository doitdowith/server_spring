package com.seoultech.capstone.chat.service;

import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.chat.repository.ChatRepository;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.message.ChatMessage;
import com.seoultech.capstone.message.ReceiveMessage;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

  private final ChatRepository chatRepository;
  private final MemberService memberService;
  private final RoomService roomService;

  public ReceiveMessage saveChat(ChatMessage chatMessage) {
    Member member = memberService.findMemberById(chatMessage.getMemberId());
    Room room = roomService.findRoomById(chatMessage.getRoomId());
    Chat chat = chatRepository.save(chatMessage.toEntity(member, room));

    return ReceiveMessage.from(member, chat);
  }


}
