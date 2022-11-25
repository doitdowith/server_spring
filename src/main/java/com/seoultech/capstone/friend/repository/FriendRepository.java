package com.seoultech.capstone.friend.repository;

import com.seoultech.capstone.friend.Friend;
import com.seoultech.capstone.member.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Friend.Key> {

  List<Friend> findFriendsByMember(Member member);

  List<Friend> findFriendsByFollower(Member follower);

  Optional<Friend> findFriendByMemberAndFollower(Member member, Member follower);

}
