package com.seoultech.capstone.member.service;

import com.seoultech.capstone.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPage {

  private String memberId;

  private String name;

  private Integer participationCount;

  private Integer friendCount;

  private Integer SuccessRate;

  private String dowithCode;

  private String profileImage;

  public static MyPage from(Member member, Integer friendCount, int successRate) {
    return new MyPage(member.getId(), member.getName(), member.getParticipationCount(),
        friendCount, successRate,
        member.getDowithCode(), member.getProfileImage().getStoreFileName());
  }

}
