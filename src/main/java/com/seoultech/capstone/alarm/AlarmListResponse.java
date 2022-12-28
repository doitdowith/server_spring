package com.seoultech.capstone.alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AlarmListResponse {

    private List<?> data;
    private int count;

    public static AlarmListResponse from(List<?> collect) {
        return new AlarmListResponse(collect, collect.size());
    }
}
