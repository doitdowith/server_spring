package com.seoultech.capstone.chat;

import com.seoultech.capstone.BaseTimeEntity;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Chat extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  private Room room;

  public Chat(String content, Member member, Room room) {
    this.content = content;
    this.member = member;
    this.room = room;
    room.getChatList().add(this);
  }

  protected Chat() {
  }
}
