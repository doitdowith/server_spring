package com.seoultech.capstone.room.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomListResponse {

  private List<?> data;

  public static RoomListResponse from(List<?> data) {
    return new RoomListResponse(data);
  }

}
