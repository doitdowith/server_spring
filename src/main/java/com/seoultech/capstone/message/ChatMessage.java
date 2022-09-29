package com.seoultech.capstone.message;

import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatMessage {

  private String contents;
  private String memberId;
  private String roomId;

  public Chat toEntity(Member member, Room room) {
    return new Chat(this.contents, member, room);
  }


}
