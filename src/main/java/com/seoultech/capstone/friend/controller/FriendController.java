package com.seoultech.capstone.friend.controller;

import static com.seoultech.capstone.config.jwt.JwtFilter.AUTHORIZATION_HEADER;
import static com.seoultech.capstone.config.jwt.JwtFilter.BEARER_PREFIX;

import com.seoultech.capstone.config.jwt.JwtConfig;
import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.friend.service.FriendService;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
public class FriendController {

  private final MemberService memberService;
  private final FriendService friendService;

  @PostMapping
  public ResponseEntity<Void> sendFriendRequest(@ApiIgnore @Auth String memberId,
      @RequestBody AddFriendRequest request) {
    Member member = memberService.findMemberById(memberId);
    Member follower = memberService.findMemberById(request.getFollowerId());
    friendService.addFriend(member, follower);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/accept")
  public ResponseEntity<Void> acceptFriendRequest(
      @ApiIgnore @Auth String memberId, @RequestBody AddFriendRequest request) {
    Member follower = memberService.findMemberById(memberId);
    Member member = memberService.findMemberById(request.getFollowerId());
    friendService.acceptFriend(member, follower);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/delete/{followerId}")
  public ResponseEntity<Void> deleteFriend(@ApiIgnore @Auth String memberId,
      @PathVariable String followerId) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/my")
  public ResponseEntity<FriendListResponse> listAllMyFriend(
      HttpServletRequest servletRequest) {
    String token = resolveToken(servletRequest);
    String memberId = validateToken(token);
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(FriendListResponse.from(friendService.getMyFriendList(member)));
  }

  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
      return bearerToken.substring(7);
    }
    return null;
  }

  private String validateToken(String token) {

    byte[] decode = Decoders.BASE64.decode(JwtConfig.JWT_SECRET);
    Key key = Keys.hmacShaKeyFor(decode);

    try {
      Claims claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
      return claims.get("jti", String.class);
    } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
      throw e;
    }
  }


}
