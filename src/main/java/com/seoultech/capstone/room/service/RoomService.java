package com.seoultech.capstone.room.service;

import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.room.controller.RoomRequest;
import com.seoultech.capstone.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  private final MemberService memberService;

  public void save(RoomRequest.CreateDto createDto) {

    Member member = memberService.findMemberById(createDto.getMasterId());
    Room room = createDto.toEntity(member);
    roomRepository.save(room);

  }

  public Room findRoomById(String roomId) {
    return roomRepository.findById(roomId)
        .orElseThrow();
  }


}
