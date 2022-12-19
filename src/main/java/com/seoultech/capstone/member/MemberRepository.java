package com.seoultech.capstone.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

  Optional<Member> findByEmail(String email);

  Optional<Member> findMemberByDowithCode(String dowithCode);

}
