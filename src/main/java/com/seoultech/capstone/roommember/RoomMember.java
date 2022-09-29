package com.seoultech.capstone.roommember;

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
public class RoomMember {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Room room;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  private Boolean accept;

  public void approve() {
    this.accept = true;
  }

  public void cancel() {
    this.accept = false;
  }

  public RoomMember(Room room, Member member, Boolean isMaster) {
    this.room = room;
    this.member = member;
    if (isMaster) {
      this.accept = true;
    }
  }

  protected RoomMember() {
  }
}
