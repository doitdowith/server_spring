package com.seoultech.capstone.alarm;

import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmService {

  private final AlarmRepository alarmRepository;

  public void save(Room room, Member member, Member sender) {
    alarmRepository.save(new Alarm(member, sender, Category.ROOM));
  }

}
