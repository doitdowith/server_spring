package com.seoultech.capstone.exception;

public class NotCreateRoomException extends RuntimeException {

  private static final String MESSAGE = "방을 생성할 수 없습니다.";

  public NotCreateRoomException() {
    super(MESSAGE);
  }

}
