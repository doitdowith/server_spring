package com.seoultech.capstone.room.controller;

import com.seoultech.capstone.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;


  public ResponseEntity<Void> makeRoom() {
    return null;
  }


}
