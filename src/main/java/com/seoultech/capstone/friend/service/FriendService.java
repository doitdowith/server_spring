package com.seoultech.capstone.friend.service;

import com.seoultech.capstone.friend.Friend;
import com.seoultech.capstone.friend.repository.FriendRepository;
import com.seoultech.capstone.member.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {

  private final FriendRepository friendRepository;

  public List<Friend> findFriendsByMember(Member member) {
    return friendRepository.findFriendsByMember(member);
  }

  public List<Friend> findFriendsByFollower(Member follower) {
    return friendRepository.findFriendsByFollower(follower);
  }

}
