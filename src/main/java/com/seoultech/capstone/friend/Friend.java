package com.seoultech.capstone.friend;


import com.seoultech.capstone.member.Member;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {

  @EmbeddedId
  private Key key;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("memberId")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("followerId")
  private Member follower;

  private Boolean isAccept;

  public void setIsAccept(Boolean isAccept) {
    this.isAccept = isAccept;
  }

  @Embeddable
  @AllArgsConstructor
  @Getter
  @EqualsAndHashCode
  public static class Key implements Serializable {

    private String memberId;
    private String followerId;

    protected Key() {
    }
  }


}
