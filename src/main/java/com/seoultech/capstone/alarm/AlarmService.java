package com.seoultech.capstone.alarm;

import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmService {

  private final AlarmRepository alarmRepository;

  public void save(Room room, Member member, Member sender) {
    alarmRepository.save(new Alarm(member, sender, Category.ROOM));
  }

  public void savefriendAlarm(Member member, Member sender) {
    alarmRepository.save(new Alarm(member, sender, Category.FRIEND));
  }

  public AlarmListResponse listAlarm(Member member) {
    List<Alarm> alarmList = alarmRepository.findAlarmsBySender(member);
    List<AlarmDto> collect = alarmList.stream()
            .map(AlarmDto::from)
            .collect(Collectors.toList());
    return AlarmListResponse.from(collect);
  }

}
