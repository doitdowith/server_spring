package com.seoultech.capstone.alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class AlarmDto {

    private String date;
    private String name;

    public static AlarmDto from(Alarm alarm) {
        return new AlarmDto(alarm.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), alarm.getMember().getName());
    }

}
