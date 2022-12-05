package com.seoultech.capstone.member.controller;

import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.member.service.MyPage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/mypage")
  public ResponseEntity<MyPage> myPage(@ApiIgnore @Auth String memberId) {

    return ResponseEntity.ok().build();
  }

//  @PatchMapping
//  public ResponseEntity<Void> editProfile() {
//    return ResponseEntity.ok().build();
//  }
//
//  @DeleteMapping
//  public ResponseEntity<Void> deleteMember() {
//    return ResponseEntity.ok().build();
//  }


}
