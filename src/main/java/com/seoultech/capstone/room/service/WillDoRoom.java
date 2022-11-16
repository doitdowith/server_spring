package com.seoultech.capstone.room.service;

import com.seoultech.capstone.room.Room;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WillDoRoom {

  private String id;

  private String title;

  private String description;

  private String color;

  private LocalDate startDate;

  public static WillDoRoom from(Room room) {
    return new WillDoRoom(room.getId(), room.getTitle(), room.getDescription(), room.getColor(),
        room.getStartDate());
  }

}
