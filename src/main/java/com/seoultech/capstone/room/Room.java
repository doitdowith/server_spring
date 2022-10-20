package com.seoultech.capstone.room;

import com.seoultech.capstone.BaseTimeEntity;
import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.member.Member;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Room extends BaseTimeEntity {

  @Id
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member master;

  private String title;

  private String description;

  private String color;

  private Integer certificationCount;

  private LocalDate startDate;

  @OneToMany(mappedBy = "room", orphanRemoval = true)
  private List<Chat> chatList;

  public Room(Member master, String title, String description, String color,
      Integer certificationCount, LocalDate startDate) {
    this.id = UUID.randomUUID().toString();
    this.master = master;
    this.title = title;
    this.description = description;
    this.color = color;
    this.certificationCount = certificationCount;
    this.startDate = startDate;
  }

  protected Room() {
  }
}
