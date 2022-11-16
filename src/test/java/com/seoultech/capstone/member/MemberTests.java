package com.seoultech.capstone.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTests {

  @Test
  @DisplayName("생성자 난수 테스트")
  void memberTest() {

    Member member = new Member("조재영", "jdyj@naver.com", Role.USER);
    System.out.println(member.getDowithCode());

  }

}