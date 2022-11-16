package com.seoultech.capstone.room.service;

import com.seoultech.capstone.room.Room;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DoingRoom {

  private String id;

  private String title;

  private String description;

  private String color;

  private LocalDate startDate;

  public static DoingRoom from(Room room) {
    return new DoingRoom(room.getId(), room.getTitle(), room.getDescription(), room.getColor(),
        room.getStartDate());
  }

}
