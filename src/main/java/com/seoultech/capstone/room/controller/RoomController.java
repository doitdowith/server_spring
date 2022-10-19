package com.seoultech.capstone.room.controller;

import com.seoultech.capstone.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

  private final RoomService roomService;

  @PostMapping
  public ResponseEntity<Void> makeRoom() {
    return null;
  }

  @SubscribeMapping("/{roomId}")
  public ResponseEntity<Void> enterRoom(@DestinationVariable Long roomId) {
    System.out.println(roomId);
    return null;
  }


}
