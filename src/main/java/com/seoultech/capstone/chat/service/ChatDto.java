package com.seoultech.capstone.chat.service;

import com.seoultech.capstone.chat.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class ChatDto {

  private String profileImage;

  private String name;

  private boolean isMe;

  private String contents;

  private String certificationImage;

  private String time;

  public static ChatDto from(Chat chat, boolean isMe) {

    if (chat.getImage() == null) {
      return new ChatDto(chat.getMember().getProfileImage().getStoreFileName(),
              chat.getMember().getName(), isMe,
              chat.getContent(), null, chat.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    return new ChatDto(chat.getMember().getProfileImage().getStoreFileName(),
        chat.getMember().getName(), isMe,
        chat.getContent(), chat.getImage().getStoreFileName(), chat.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
  }

}
