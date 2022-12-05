package com.seoultech.capstone.member.service;

import com.seoultech.capstone.exception.NotExistMemberException;
import com.seoultech.capstone.friend.service.FriendService;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.MemberRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;
  private final FriendService friendService;

  public MyPage findMyPage(String memberId) {
    Member member = findMemberById(memberId);
    return MyPage.from(member);
  }

  public Member findMemberById(String memberId) {
    return memberRepository.findById(memberId).orElseThrow(NotExistMemberException::new);
  }

}
