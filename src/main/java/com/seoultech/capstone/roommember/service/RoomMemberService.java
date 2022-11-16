package com.seoultech.capstone.roommember.service;

import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.roommember.RoomMember;
import com.seoultech.capstone.roommember.repository.RoomMemberRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomMemberService {

  private final RoomMemberRepository roomMemberRepository;

  public void save() {

  }

  public List<Room> findDoingRoomByMember(Member member) {
    List<RoomMember> roomMemberList = roomMemberRepository.findRoomMembersByMember(member);
    return roomMemberList.stream()
        .map(RoomMember::getRoom)
        .filter(room -> room.getStartDate().compareTo(LocalDate.now()) >= 0)
        .collect(Collectors.toList());
  }

  public List<Room> findWillDoRoomByMember(Member member) {
    List<RoomMember> roomMemberList = roomMemberRepository.findRoomMembersByMember(member);
    return roomMemberList.stream()
        .map(RoomMember::getRoom)
        .filter(room -> room.getStartDate().compareTo(LocalDate.now()) < 0)
        .collect(Collectors.toList());
  }

  public List<Room> findDoneByMember(Member member) {
    List<RoomMember> roomMemberList = roomMemberRepository.findRoomMembersByMember(member);
    return roomMemberList.stream()
        .map(RoomMember::getRoom)
        .filter(room -> room.getStartDate().plusDays(7L).compareTo(LocalDate.now()) > 0)
        .collect(Collectors.toList());
  }

}
