package com.seoultech.capstone.chat.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatListAllResponse {

  private List<?> data;

  public static ChatListAllResponse from(List<?> collect) {
    return new ChatListAllResponse(collect);
  }

}
