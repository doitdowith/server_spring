package com.seoultech.capstone.member;

import com.seoultech.capstone.friend.Friend;
import com.seoultech.capstone.image.Image;
import com.seoultech.capstone.room.Room;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Formula;

@Entity
@Getter
public class Member {

  @Id
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @OneToOne(fetch = FetchType.EAGER)
  private Image profileImage;

  private String dowithCode;

  @OneToMany(mappedBy = "member")
  private List<Friend> friendList;

  @Formula("(select count(1) from room_member r where r.member_id = id)")
  private Integer participationCount;

  @Formula("(select count(1) from friend f where f.member_id = id)")
  private Integer friendCount;

  @Builder
  public Member(String name, String email) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.email = email;
    this.dowithCode = new Random().ints(48, 123)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90))
        .limit(7)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  public void setProfileImage(Image profileImage) {
    this.profileImage = profileImage;
  }

  protected Member() {
  }
}
