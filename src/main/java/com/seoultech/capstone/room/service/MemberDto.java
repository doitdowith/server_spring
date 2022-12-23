package com.seoultech.capstone.room.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDto {

  private List<String> nameList;

  private List<String> imageList;

}
