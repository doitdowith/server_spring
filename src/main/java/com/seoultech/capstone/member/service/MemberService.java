package com.seoultech.capstone.member.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.config.jwt.TokenDto;
import com.seoultech.capstone.config.jwt.TokenProvider;
import com.seoultech.capstone.exception.NotExistMemberException;
import com.seoultech.capstone.friend.Friend;
import com.seoultech.capstone.friend.service.FriendService;
import com.seoultech.capstone.image.Image;
import com.seoultech.capstone.image.ImageService;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.MemberRepository;


import com.seoultech.capstone.member.controller.LoginResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.room.service.RoomService;
import com.seoultech.capstone.roommember.service.RoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;
  private final TokenProvider tokenProvider;
  private final FriendService friendService;
  private final ImageService imageService;
  private final RestTemplate restTemplate;
  private final RoomMemberService roomMemberService;

  public MyPage findMyPage(String memberId) {
    Member member = findMemberById(memberId);

    List<Friend> friends1 = friendService.findFriendsByMember(member);
    List<Friend> friends2 = friendService.findFriendsByFollower(member);


    List<Room> roomList = roomMemberService.findRoomListByMember(member);

    int result = 0;
    for (Room room : roomList) {

      int count = 0;
      List<Chat> chatList = room.getChatList();
      List<Member> collect = chatList.stream()
              .filter(chat -> chat.getImage() != null)
              .map(Chat::getMember)
              .collect(Collectors.toList());

      for (Member member1 : collect) {
        if (member1.getId().equals(memberId)) {
            count++;
        }
      }

      if (room.getCertificationCount() <= count) {
        result++;
      }

    }
    int successRate = 0;
    if (roomList.size() != 0) {
      successRate = result / roomList.size() * 100;
    }

    return MyPage.from(member, friends1.size() + friends2.size(), successRate);
  }

  public Member findMemberById(String memberId) {
    return memberRepository.findById(memberId).orElseThrow(NotExistMemberException::new);
  }

  public LoginResponse login(String accessToken) {
    String apiUrl = "https://kapi.kakao.com/v2/user/me";
    String responseBody = get(apiUrl, accessToken);

    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse(responseBody);

    JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
    JsonObject profile = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject();

    String name = profile.getAsJsonObject().get("nickname").getAsString();
    String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

    Optional<Member> memberOptional = memberRepository.findByEmail(email);

    Member member;
    if (memberOptional.isPresent()) {
      member = memberOptional.get();
    } else {
      Image profileImage = imageService.findDefaultProfileImage();
      member = memberRepository.save(new Member(name, email));
      member.setProfileImage(profileImage);
    }

    TokenDto tokenDto = tokenProvider.generateToken(member.getId());

    return LoginResponse.from(tokenDto, member);
  }

  public Member findByDowithCode(String dowithCode) {
    return memberRepository.findMemberByDowithCode(dowithCode)
        .orElseThrow(NotExistMemberException::new);
  }

  private String get(String apiUrl, String accessToken) {

    try {
      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "Bearer " + accessToken);

      HttpEntity entity = new HttpEntity(headers);
      ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity,
          String.class);

      return response.getBody();
    } catch (HttpStatusCodeException e) {
      throw new RuntimeException("API 요청과 응답 실패", e);
    }
  }

}
