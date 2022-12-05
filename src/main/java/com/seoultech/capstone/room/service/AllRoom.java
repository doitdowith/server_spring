package com.seoultech.capstone.room.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllRoom {

  private List<DoingRoom> doingRoomList;
  private List<WillDoRoom> willDoRoomList;
  private List<DoneRoom> doneRoomList;

  public static AllRoom from(List<DoingRoom> doingRoomList,
      List<WillDoRoom> willDoRoomList,
      List<DoneRoom> doneRoomList) {
    return new AllRoom(doingRoomList, willDoRoomList, doneRoomList);
  }

}
