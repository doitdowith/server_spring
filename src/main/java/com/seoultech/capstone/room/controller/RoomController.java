package com.seoultech.capstone.room.controller;

import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.room.controller.dto.RoomListResponse;
import com.seoultech.capstone.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/doing")
  public ResponseEntity<RoomListResponse> doingRoom(@Auth String memberId) {
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(RoomListResponse.from(roomService.doingRoom(member)));
  }

  @GetMapping("/willdo")
  public ResponseEntity<RoomListResponse> willdoRoom(@Auth String memberId) {
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(RoomListResponse.from(roomService.willDoRoom(member)));
  }

  @GetMapping("/done")
  public ResponseEntity<RoomListResponse> doneRoom(@Auth String memberId) {
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(RoomListResponse.from(roomService.doneRoom(member)));
  }

//  @DeleteMapping("/{roomId}")
//  public ResponseEntity<Void> deleteRoom() {
//    return ResponseEntity.status(HttpStatus.OK).build();
//  }

}
