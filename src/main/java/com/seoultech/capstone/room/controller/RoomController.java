package com.seoultech.capstone.room.controller;

import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

  private final RoomService roomService;
  private final MemberService memberService;

  @PostMapping
  public ResponseEntity<Void> makeRoom(@Auth String memberId,
      @RequestBody RoomRequest.CreateDto request) {
    roomService.save(request.toEntity(memberService.findMemberById(memberId)));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @SubscribeMapping("/{roomId}")
  public ResponseEntity<Void> enterRoom(@DestinationVariable Long roomId) {
    System.out.println(roomId);
    return null;
  }


}
