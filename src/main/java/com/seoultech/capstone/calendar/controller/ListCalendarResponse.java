package com.seoultech.capstone.calendar.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ListCalendarResponse {

  private List<?> data;
  private int count;

  public static ListCalendarResponse from(List<?> collect) {
    return new ListCalendarResponse(collect, collect.size());
  }

}
