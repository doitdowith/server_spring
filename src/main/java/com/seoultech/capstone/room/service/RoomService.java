package com.seoultech.capstone.room.service;

import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.room.repository.RoomRepository;
import com.seoultech.capstone.roommember.service.RoomMemberService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

  private final RoomRepository roomRepository;
  private final RoomMemberService roomMemberService;

  public void save(Room room) {
    roomRepository.save(room);
  }

  public Room findRoomById(String roomId) {
    return roomRepository.findById(roomId)
        .orElseThrow();
  }

  public List<DoingRoom> doingRoom(Member member) {
    List<Room> roomList = roomMemberService.findDoingRoomByMember(member);
    return roomList.stream()
        .map(DoingRoom::from)
        .collect(Collectors.toList());
  }

  public List<WillDoRoom> willDoRoom(Member member) {
    List<Room> roomList = roomMemberService.findWillDoRoomByMember(member);
    return roomList.stream()
        .map(WillDoRoom::from)
        .collect(Collectors.toList());
  }

  public List<DoneRoom> doneRoom(Member member) {
    List<Room> roomList = roomMemberService.findDoneByMember(member);
    return roomList.stream()
        .map(DoneRoom::from)
        .collect(Collectors.toList());
  }


}
