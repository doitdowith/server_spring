package com.seoultech.capstone.room.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.seoultech.capstone.member.Member;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class RoomControllerTests extends StompSupport {

  @Test
  public void findUsers() throws ExecutionException, InterruptedException, TimeoutException {
    /* GIVEN */
    MessageFrameHandler<Member[]> handler = new MessageFrameHandler<>(Member[].class);
    this.stompSession.subscribe("/sub/room/1", handler);

    /* WHEN */
    this.stompSession.send("/pub/room/1", "");

    /* THEN */
    Member[] members = handler.getCompletableFuture().get(3, TimeUnit.SECONDS);
    List<Member> userList = List.of(members);

    assertThat(userList).isNotNull();
    assertThat(userList.size()).isGreaterThan(0);
  }

}
