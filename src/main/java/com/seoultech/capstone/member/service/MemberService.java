package com.seoultech.capstone.member.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seoultech.capstone.TokenDto;
import com.seoultech.capstone.exception.NotExistMemberException;
import com.seoultech.capstone.jwt.TokenProvider;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.Role;
import com.seoultech.capstone.member.controller.dto.request.KakaoOAuthRequest;
import com.seoultech.capstone.member.controller.dto.response.MemberOAuthResponse;
import com.seoultech.capstone.member.repository.MemberRepository;
import io.vavr.control.Either;
import java.util.Optional;
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
  private final RestTemplate restTemplate;
  private final TokenProvider tokenProvider;

  public Either<String, MemberOAuthResponse> kakaoLogin(KakaoOAuthRequest request) {
    String apiUrl = "https://kapi.kakao.com/v2/user/me";
    String responseBody = get(apiUrl, request.getAccessToken());

    JsonParser parser = new JsonParser();
    JsonElement element = parser.parse(responseBody);

    JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
    JsonObject profile = kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject();

    String name = profile.getAsJsonObject().get("nickname").getAsString();
    String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

    Optional<Member> memberOptional = memberRepository.findByEmail(email);

    Member member = memberOptional.orElseGet(
        () -> memberRepository.save(new Member(name, email, Role.USER)));

    TokenDto token = null;
    return Either.right(MemberOAuthResponse.from(token));
  }

  public Member findMemberById(String memberId) {
    return memberRepository.findById(memberId).orElseThrow(NotExistMemberException::new);
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
