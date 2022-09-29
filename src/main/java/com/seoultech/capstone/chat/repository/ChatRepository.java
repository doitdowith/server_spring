package com.seoultech.capstone.chat.repository;

import com.seoultech.capstone.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
