package com.seoultech.capstone.room.service;

import com.seoultech.capstone.room.Room;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DoingRoom {

  private String id;

  private String title;

  private String description;

  private String color;

  private String startDate;

  private int participants;

  private List<String> nameList;

  private List<String> imageList;

  public static DoingRoom from(Room room, List<String> nameList, List<String> imageList) {
    return new DoingRoom(room.getId(), room.getTitle(), room.getDescription(), room.getColor(),
        room.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), nameList.size(),
        nameList,
        imageList);
  }

}
