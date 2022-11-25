package com.seoultech.capstone.friend.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendListResponse {

  private List<?> data;

  public static FriendListResponse from(List<?> data) {
    return new FriendListResponse(data);
  }


}
