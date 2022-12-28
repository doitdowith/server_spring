package com.seoultech.capstone.room.service;

import com.seoultech.capstone.exception.NotCreateRoomException;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.room.repository.RoomRepository;
import com.seoultech.capstone.roommember.service.RoomMemberService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  public String makeRoom(Room room, List<String> participants) {
    Room savedRoom = roomRepository.save(room);
    roomMemberService.save(savedRoom, participants);

    return savedRoom.getId();
  }

  public Room findRoomById(String roomId) {
    return roomRepository.findById(roomId)
        .orElseThrow();
  }

  public AllRoom allRoom(Member member) {

    List<DoingRoom> doingRoomList = doingRoom(member);
    List<WillDoRoom> willDoRoomList = willDoRoom(member);
    List<DoneRoom> doneRoomList = doneRoom(member);

    return AllRoom.from(doingRoomList, willDoRoomList, doneRoomList);
  }

  public List<DoingRoom> doingRoom(Member member) {
    List<Room> roomList = roomMemberService.findDoingRoomByMember(member);

    List<DoingRoom> collect = new ArrayList<>();
    for (Room room : roomList) {
      List<Member> memberList = roomMemberService.findMemberListByRoom(room);
      List<String> nameList = new ArrayList<>();
      List<String> imageList = new ArrayList<>();
      Member master = room.getMaster();
      nameList.add(master.getName());
      imageList.add(master.getProfileImage().getStoreFileName());
      for (Member tempMember : memberList) {
        if (tempMember.getName().equals(master.getName())) {
          continue;
        }
        nameList.add(tempMember.getName());
        imageList.add(tempMember.getProfileImage().getStoreFileName());
      }

      collect.add(DoingRoom.from(room, nameList, imageList));
    }

    return collect;
  }

  public List<WillDoRoom> willDoRoom(Member member) {
    List<Room> roomList = roomMemberService.findWillDoRoomByMember(member);
    List<WillDoRoom> collect = new ArrayList<>();
    for (Room room : roomList) {
      List<Member> memberList = roomMemberService.findMemberListByRoom(room);
      List<String> nameList = new ArrayList<>();
      List<String> imageList = new ArrayList<>();
      Member master = room.getMaster();
      nameList.add(master.getName());
      imageList.add(master.getProfileImage().getStoreFileName());
      for (Member tempMember : memberList) {
        if (tempMember.getName().equals(master.getName())) {
          continue;
        }
        nameList.add(tempMember.getName());
        imageList.add(tempMember.getProfileImage().getStoreFileName());
      }

      collect.add(WillDoRoom.from(room, nameList, imageList));
    }

    return collect;
  }

  public List<DoneRoom> doneRoom(Member member) {
    List<Room> roomList = roomMemberService.findDoneByMember(member);
    List<DoneRoom> collect = new ArrayList<>();
    for (Room room : roomList) {
      List<Member> memberList = roomMemberService.findMemberListByRoom(room);
      List<String> nameList = new ArrayList<>();
      List<String> imageList = new ArrayList<>();
      Member master = room.getMaster();
      nameList.add(master.getName());
      imageList.add(master.getProfileImage().getStoreFileName());
      for (Member tempMember : memberList) {
        if (tempMember.getName().equals(master.getName())) {
          continue;
        }
        nameList.add(tempMember.getName());
        imageList.add(tempMember.getProfileImage().getStoreFileName());
      }

      collect.add(DoneRoom.from(room, nameList, imageList));
    }

    return collect;
  }


}
