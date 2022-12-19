package com.seoultech.capstone.calendar.service;

import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.roommember.service.RoomMemberService;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CalenderService {

  private final RoomMemberService roomMemberService;

  public List<MyCalendar> roomList(Member member) {

    Map<String, List<String>> map = new TreeMap<>();
    List<Room> roomListByMember = roomMemberService.findRoomListByMember(member);

    roomListByMember.sort(Comparator.comparing(Room::getStartDate));

    for (Room room : roomListByMember) {
      String date = room.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      int count = 0;
      if (map.containsKey(date)) {
        List<String> collect = map.get(date);
        count = collect.size();
        collect.add(room.getTitle());
        map.put(date, collect);
      } else {
        List<String> collect = new ArrayList<>();
        collect.add(room.getTitle());
        map.put(date, collect);
      }
      for (int i = 1; i < 7; i++) {
        String format = room.getStartDate().plusDays(i)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (map.containsKey(format)) {
          map.get(format).add(room.getTitle());
        } else {
          List<String> collect = new ArrayList<>();
          for (int j = 0; j < count; j++) {
            collect.add("");
          }
          collect.add(room.getTitle());
          map.put(format, collect);
        }
      }
    }

    return map.entrySet()
        .stream()
        .map(MyCalendar::from)
        .collect(Collectors.toList());

  }

}
