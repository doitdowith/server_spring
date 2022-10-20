package com.seoultech.capstone.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

  @GetMapping("/mypage")
  public ResponseEntity<Void> myPage() {
    return ResponseEntity.ok().build();
  }

  @PatchMapping
  public ResponseEntity<Void> editProfile() {
    return ResponseEntity.ok().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteMember() {
    return ResponseEntity.ok().build();
  }


}
