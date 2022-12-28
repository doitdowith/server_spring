package com.seoultech.capstone.chat.controller;

import com.seoultech.capstone.chat.Chat;
import com.seoultech.capstone.image.Image;
import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class CertificationRequest {

    private String contents;

    private MultipartFile file;

    private String roomId;

    public Chat toEntity(Member member, Image image, Room room) {
        return new Chat(image, this.contents, member, room);
    }

}
