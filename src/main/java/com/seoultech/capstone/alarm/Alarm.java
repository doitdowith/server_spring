package com.seoultech.capstone.alarm;

import com.seoultech.capstone.BaseTimeEntity;
import com.seoultech.capstone.member.Member;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Alarm extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Member member;

  @ManyToOne
  private Member sender;

  @Enumerated(EnumType.STRING)
  private Category category;

  public Alarm(Member member, Member sender, Category category) {
    this.member = member;
    this.sender = sender;
    this.category = category;
  }

  protected Alarm() {

  }

}
