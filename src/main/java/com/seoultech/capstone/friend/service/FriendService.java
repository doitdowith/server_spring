package com.seoultech.capstone.friend.service;

import com.seoultech.capstone.friend.Friend;
import com.seoultech.capstone.friend.Friend.Key;
import com.seoultech.capstone.friend.repository.FriendRepository;
import com.seoultech.capstone.member.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {

  private final FriendRepository friendRepository;

  public void addFriend(Member member, Member follower) {
    friendRepository.save(
        new Friend(new Key(member.getId(), follower.getId()), member, follower, false));
  }

  public void acceptFriend(Member member, Member follower) {
    Friend friend = friendRepository.findFriendByMemberAndFollower(member, follower)
        .orElseThrow(() -> new IllegalStateException("존재하지 않는 친구 요청입니다."));
    friend.setIsAccept(true);
  }

  @Transactional(readOnly = true)
  public List<InviteFriend> getMyFriendList(Member member) {

    List<Friend> friends = findFriendsByFollower(member);
    friends.addAll(findFriendsByMember(member));

    List<InviteFriend> collect = new ArrayList<>();
    for (Friend friend : friends) {
      Member friendMember;
      if (friend.getMember().getId().equals(member.getId())) {
        friendMember = friend.getFollower();
      } else {
        friendMember = friend.getMember();
      }
      collect.add(new InviteFriend(friendMember.getId(),
          friendMember.getProfileImage().getStoreFileName(),
          friendMember.getName()));
    }

    return collect;

  }

  public List<Friend> findFriendsByMember(Member member) {
    return friendRepository.findFriendsByMember(member);
  }

  public List<Friend> findFriendsByFollower(Member follower) {
    return friendRepository.findFriendsByFollower(follower);
  }

}
