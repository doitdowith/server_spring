package com.seoultech.capstone.chat.service;

import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.chat.controller.CertificationRequest;
import com.seoultech.capstone.chat.dto.ChatListAllResponse;
import com.seoultech.capstone.chat.repository.ChatRepository;
import com.seoultech.capstone.image.Category;
import com.seoultech.capstone.image.Image;
import com.seoultech.capstone.image.ImageService;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.message.ChatMessage;
import com.seoultech.capstone.message.ReceiveMessage;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.room.service.RoomService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

  private final ChatRepository chatRepository;
  private final RoomService roomService;
  private final ImageService imageService;

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

  public void certification(Member member, CertificationRequest request) throws IOException {

    Image image = imageService.storeFile(request.getFile(), Category.CERTIFICATION);
    Room room = roomService.findRoomById(request.getRoomId());
    Chat chat = request.toEntity(member, image, room);
    chatRepository.save(chat);

  }


}
