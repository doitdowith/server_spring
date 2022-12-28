package com.seoultech.capstone.alarm;

import com.seoultech.capstone.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAlarmsBySender(Member sender);

}
