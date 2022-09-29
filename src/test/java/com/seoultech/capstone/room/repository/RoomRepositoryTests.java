package com.seoultech.capstone.room.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.seoultech.capstone.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RoomRepositoryTests {

  @Autowired
  RoomRepository roomRepository;


}