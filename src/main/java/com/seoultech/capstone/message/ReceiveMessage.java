package com.seoultech.capstone.message;

import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.member.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReceiveMessage {

  private final String contents;

  private final String nickname;

  private final String profileImg;

  private final LocalDateTime sendTime;

  public static ReceiveMessage from(Member member, Chat chat) {
    return new ReceiveMessage(chat.getContent(), member.getName(),
        member.getProfileImage().getStoreFileName(), chat.getCreatedDate());
  }
}
