package com.seoultech.capstone.member.service;

import com.seoultech.capstone.member.controller.dto.request.KakaoOAuthRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceTests {

  @Autowired
  MemberService memberService;

  @Test
  @Transactional
  void kakaoLoginTest() {
    KakaoOAuthRequest kakaoOAuthRequest = new KakaoOAuthRequest(
        "");
    var result = memberService.kakaoLogin(kakaoOAuthRequest);


  }


}