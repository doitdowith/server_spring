package com.seoultech.capstone.room.controller.dto;

import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RoomRequest {

  @Getter
  @AllArgsConstructor
  public static class CreateDto {

    private String masterId;

    private String title;

    private String description;

    private String color;

    private Integer certificationCount;

    private LocalDate startDate;

    private List<String> participants;

    public Room toEntity(Member master) {
      return new Room(master, this.title, this.description, this.color, this.certificationCount,
          this.startDate);
    }

  }

}
