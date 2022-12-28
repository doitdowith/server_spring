package com.seoultech.capstone.alarm;

import com.seoultech.capstone.config.jwt.JwtConfig;
import com.seoultech.capstone.member.service.MemberService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;

import static com.seoultech.capstone.config.jwt.JwtFilter.AUTHORIZATION_HEADER;
import static com.seoultech.capstone.config.jwt.JwtFilter.BEARER_PREFIX;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarm")
public class AlarmController {

    private final AlarmService alarmService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<AlarmListResponse> alarmList(HttpServletRequest servletRequest) {
        String memberId = validateToken(resolveToken(servletRequest));
        return ResponseEntity.ok()
                .body(alarmService.listAlarm(memberService.findMemberById(memberId)));
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
