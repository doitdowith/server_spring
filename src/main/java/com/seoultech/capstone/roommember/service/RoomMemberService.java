package com.seoultech.capstone.roommember.service;

import com.seoultech.capstone.alarm.AlarmService;
import com.seoultech.capstone.exception.NotExistMemberException;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.MemberRepository;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.roommember.RoomMember;
import com.seoultech.capstone.roommember.repository.RoomMemberRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomMemberService {

  private final RoomMemberRepository roomMemberRepository;
  private final AlarmService alarmService;
  private final MemberRepository memberRepository;

  public void save(Room room, List<String> participants) {

    List<RoomMember> collect = new ArrayList<>();
    Member sender = memberRepository.findById(participants.get(0)).orElseThrow(NotExistMemberException::new);
    roomMemberRepository.save(new RoomMember(room, sender, true));
    for (int i = 1; i < participants.size(); i++) {
      Member member = memberRepository.findById(participants.get(i)).orElseThrow(NotExistMemberException::new);
      collect.add(new RoomMember(room, member, false));
      alarmService.save(room, member, sender);
    }

    roomMemberRepository.saveAll(collect);
  }

  public List<Room> findDoingRoomByMember(Member member) {
    List<RoomMember> roomMemberList = roomMemberRepository.findRoomMembersByMember(member);
    return roomMemberList.stream()
        .map(RoomMember::getRoom)
        .filter(room -> (room.getStartDate().isBefore(LocalDate.now()) || room.getStartDate()
            .isEqual(LocalDate.now())) && room.getStartDate().plusDays(7L).isAfter(LocalDate.now()))
        .collect(Collectors.toList());
  }

  public List<Room> findWillDoRoomByMember(Member member) {
    List<RoomMember> roomMemberList = roomMemberRepository.findRoomMembersByMember(member);
    return roomMemberList.stream()
        .map(RoomMember::getRoom)
        .filter(room -> room.getStartDate().isAfter(LocalDate.now()))
        .collect(Collectors.toList());
  }

  public List<Room> findDoneByMember(Member member) {
    List<RoomMember> roomMemberList = roomMemberRepository.findRoomMembersByMember(member);
    return roomMemberList.stream()
        .map(RoomMember::getRoom)
        .filter(room -> room.getStartDate().plusDays(7L).isBefore(LocalDate.now()))
        .collect(Collectors.toList());
  }

  public List<Room> findRoomListByMember(Member member) {
    List<RoomMember> roomMemberList = roomMemberRepository.findRoomMembersByMember(member);
    return roomMemberList.stream()
        .map(RoomMember::getRoom)
        .collect(Collectors.toList());
  }

  public List<Member> findMemberListByRoom(Room room) {
    return roomMemberRepository.findRoomMembersByRoom(room).stream()
        .map(RoomMember::getMember)
        .collect(Collectors.toList());


  }

}
