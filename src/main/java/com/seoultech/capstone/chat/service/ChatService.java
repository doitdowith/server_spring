package com.seoultech.capstone.chat.service;

import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.chat.dto.ChatListAllResponse;
import com.seoultech.capstone.chat.repository.ChatRepository;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.message.ChatMessage;
import com.seoultech.capstone.message.ReceiveMessage;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.room.service.RoomService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

  private final ChatRepository chatRepository;
  private final RoomService roomService;

  public ReceiveMessage saveChat(Member member, ChatMessage chatMessage) {
    Room room = roomService.findRoomById(chatMessage.getRoomId());
    Chat chat = chatRepository.save(chatMessage.toEntity(member, room));

    return ReceiveMessage.from(member, chat);
  }

  public List<ChatDto> listAll(Member member, String roomId) {
    Room room = roomService.findRoomById(roomId);
    List<Chat> chatList = room.getChatList();

    return chatList.stream()
        .map((chat) -> ChatDto.from(chat, chat.getMember().getId().equals(member.getId())))
        .collect(Collectors.toList());
  }


}
