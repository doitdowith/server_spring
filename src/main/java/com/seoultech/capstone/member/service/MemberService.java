package com.seoultech.capstone.member.service;

import com.seoultech.capstone.exception.NotExistMemberException;
import com.seoultech.capstone.jwt.TokenProvider;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.MemberRepository;
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
