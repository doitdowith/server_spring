package com.seoultech.capstone.chat.service;

import com.seoultech.capstone.chat.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatDto {

  private String profileImage;

  private String name;

  private boolean isMe;

  private String contents;

  public static ChatDto from(Chat chat, boolean isMe) {
    return new ChatDto(chat.getMember().getProfileImage().getStoreFileName(),
        chat.getMember().getName(), isMe,
        chat.getContent());
  }

}
