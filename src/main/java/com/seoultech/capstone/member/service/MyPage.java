package com.seoultech.capstone.member.service;

import com.seoultech.capstone.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPage {

  private String memberId;

  private Integer participationCount;

  private Integer friendCount;

  private Integer SuccessRate;

  private String dowithCode;

  public static MyPage from(Member member) {
    return new MyPage(member.getId(), member.getParticipationCount(), member.getFriendCount(), 0,
        member.getDowithCode());
  }

}
