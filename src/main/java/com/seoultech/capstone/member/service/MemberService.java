package com.seoultech.capstone.member.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seoultech.capstone.TokenDto;
import com.seoultech.capstone.exception.NotExistMemberException;
import com.seoultech.capstone.jwt.TokenProvider;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.Role;
import com.seoultech.capstone.member.MemberRepository;
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



  public Member findMemberById(String memberId) {
    return memberRepository.findById(memberId).orElseThrow(NotExistMemberException::new);
  }

}
