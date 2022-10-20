package com.seoultech.capstone.friend.controller;

import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
public class FriendController {

  private final MemberService memberService;

  @PostMapping
  public ResponseEntity<Void> sendFriendRequest(@ApiIgnore @Auth String memberId) {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/accept")
  public ResponseEntity<Void> acceptFriendRequest(
      @ApiIgnore @Auth String memberId) {
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/delete/{followerId}")
  public ResponseEntity<Void> deleteFriend(@ApiIgnore @Auth String memberId,
      @PathVariable String followerId) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/my")
  public ResponseEntity<Void> listAllMyFriend(
      @ApiIgnore @Auth String memberId) {
    return ResponseEntity.ok().build();
  }

}
