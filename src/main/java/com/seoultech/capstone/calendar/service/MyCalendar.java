package com.seoultech.capstone.calendar.service;

import com.seoultech.capstone.room.Room;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyCalendar {

  private String startDate;
  private List<String> title;

  public static MyCalendar from(Entry<String, List<String>> listEntry) {
    return new MyCalendar(listEntry.getKey(), listEntry.getValue());
  }

}
