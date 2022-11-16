package com.seoultech.capstone.room.service;

import com.seoultech.capstone.room.Room;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoneRoom {

  private String id;

  private String title;

  private String description;

  private String color;

  private LocalDate startDate;

  public static DoneRoom from(Room room) {
    return new DoneRoom(room.getId(), room.getTitle(), room.getDescription(), room.getColor(),
        room.getStartDate());
  }

}
