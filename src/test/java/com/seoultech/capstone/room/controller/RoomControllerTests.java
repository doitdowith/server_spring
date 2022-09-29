package com.seoultech.capstone.room.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.seoultech.capstone.member.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@SpringBootTest
class RoomControllerTests {

  StompSession stompSession;

  @LocalServerPort
  int port;

  WebSocketStompClient webSocketStompClient;

  @BeforeEach
  public void connect() throws ExecutionException, InterruptedException, TimeoutException {
    this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(createTransport()));
    stompSession = this.webSocketStompClient.connect("ws://localhost:8080/chat",
        new StompSessionHandlerAdapter() {
        }).get(3, TimeUnit.SECONDS);

  }

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

  private List<Transport> createTransport() {
    List<Transport> transports = new ArrayList<>(1);
    transports.add(new WebSocketTransport(new StandardWebSocketClient()));
    return transports;
  }

}