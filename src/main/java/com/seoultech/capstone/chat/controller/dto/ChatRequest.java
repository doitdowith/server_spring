package com.seoultech.capstone.chat.controller.dto;

import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ChatRequest {

  @Getter
  @AllArgsConstructor
  static public class CreateDto {

    private String memberId;

    private String contents;

    private String roomId;

    public Chat toEntity(Member member, Room room) {
      return new Chat(this.contents, member, room);
    }

  }

}
