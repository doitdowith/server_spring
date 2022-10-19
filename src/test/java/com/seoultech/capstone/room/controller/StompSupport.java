package com.seoultech.capstone.room.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.seoultech.capstone.member.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StompSupport {

  StompSession stompSession;

  @LocalServerPort
  int port;

  WebSocketStompClient webSocketStompClient;

  @BeforeEach
  public void connect() throws ExecutionException, InterruptedException, TimeoutException {
    this.webSocketStompClient = new WebSocketStompClient(new SockJsClient(createTransport()));
    this.webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
    stompSession = this.webSocketStompClient.connect("ws://localhost:" + port + "/chat",
        new StompSessionHandlerAdapter() {
        }).get(3, TimeUnit.SECONDS);

  }
  @Test
  void webSocketTest() {



  }

  @AfterEach
  public void disconnect() {
    if (this.stompSession.isConnected()) {
      this.stompSession.disconnect();
    }
  }

  private List<Transport> createTransport() {
    List<Transport> transports = new ArrayList<>(1);
    transports.add(new WebSocketTransport(new StandardWebSocketClient()));
    return transports;
  }

}