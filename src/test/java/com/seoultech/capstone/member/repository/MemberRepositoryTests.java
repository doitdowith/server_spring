package com.seoultech.capstone.member.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.seoultech.capstone.exception.NotExistMemberException;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTests {

  @Autowired
  MemberRepository memberRepository;

  Member member;

  @BeforeEach
  void init() {
    member = Member.builder()
        .email("jdyj@naver.com")
        .name("조재영")
        .role(Role.USER)
        .build();
    memberRepository.save(member);
  }

  @Test
  @DisplayName("findMemberEntityByEmail")
  void findMemberEntityByEmailTest() {
    String email = "jdyj@naver.com";
    Member member = memberRepository.findByEmail(email)
        .orElseThrow(NotExistMemberException::new);

    assertThat(member.getEmail()).isEqualTo("jdyj@naver.com");
    assertThat(member.getName()).isEqualTo("조재영");
    assertThat(member.getRoleKey()).isEqualTo(Role.USER.getKey());

  }

  @Test
  @DisplayName("findMemberEntityByEmail - 존재하지 않을 때")
  void findMemberEntityByEmailTestNotFound() {
    String email = "jdyj@naver.co";

    assertThrows(NotExistMemberException.class,
        () -> memberRepository.findByEmail(email)
            .orElseThrow(NotExistMemberException::new));
  }

}