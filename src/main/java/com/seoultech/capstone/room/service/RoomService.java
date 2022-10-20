package com.seoultech.capstone.room.service;

import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

  private final RoomRepository roomRepository;
  private final MemberService memberService;

  public void save(Room room) {
    roomRepository.save(room);
  }

  public Room findRoomById(String roomId) {
    return roomRepository.findById(roomId)
        .orElseThrow();
  }


}
