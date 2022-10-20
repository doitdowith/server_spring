package com.seoultech.capstone.alarm;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarm")
public class AlarmController {

  @GetMapping
  public ResponseEntity<Void> alarmList() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{alarmId}")
  public ResponseEntity<Void> detailAlarm() {
    return ResponseEntity.ok().build();
  }


}
