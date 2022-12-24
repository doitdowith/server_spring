package com.seoultech.capstone.room.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class RoomResponse {

  @Getter
  @AllArgsConstructor
  public static class CreateDto {

    private String roomId;

    public static CreateDto from(String roomId) {
      return new CreateDto(roomId);
    }

  }

}
