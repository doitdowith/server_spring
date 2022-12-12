package com.seoultech.capstone.room.controller;

import static com.seoultech.capstone.config.jwt.JwtFilter.AUTHORIZATION_HEADER;
import static com.seoultech.capstone.config.jwt.JwtFilter.BEARER_PREFIX;

import com.seoultech.capstone.config.jwt.JwtConfig;
import com.seoultech.capstone.config.login.Auth;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.member.service.MemberService;
import com.seoultech.capstone.room.controller.dto.RoomListResponse;
import com.seoultech.capstone.room.service.AllRoom;
import com.seoultech.capstone.room.service.RoomService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

  private final RoomService roomService;
  private final MemberService memberService;

  @PostMapping
  public ResponseEntity<Void> makeRoom(HttpServletRequest servletRequest,
      @RequestBody RoomRequest.CreateDto request) {

    String token = resolveToken(servletRequest);
    String memberId = validateToken(token);

    roomService.makeRoom(request.toEntity(memberService.findMemberById(memberId)),
        request.getParticipants());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<AllRoom> allRoom(@ApiIgnore @Auth String memberId) {
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(roomService.allRoom(member));
  }

  @GetMapping("/doing")
  public ResponseEntity<RoomListResponse> doingRoom(@ApiIgnore @Auth String memberId) {
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(RoomListResponse.from(roomService.doingRoom(member)));
  }

  @GetMapping("/willdo")
  public ResponseEntity<RoomListResponse> willdoRoom(@ApiIgnore @Auth String memberId) {
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(RoomListResponse.from(roomService.willDoRoom(member)));
  }

  @GetMapping("/done")
  public ResponseEntity<RoomListResponse> doneRoom(@ApiIgnore @Auth String memberId) {
    Member member = memberService.findMemberById(memberId);
    return ResponseEntity.ok()
        .body(RoomListResponse.from(roomService.doneRoom(member)));
  }

  //  @DeleteMapping("/{roomId}")
//  public ResponseEntity<Void> deleteRoom() {
//    return ResponseEntity.status(HttpStatus.OK).build();
//  }
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
