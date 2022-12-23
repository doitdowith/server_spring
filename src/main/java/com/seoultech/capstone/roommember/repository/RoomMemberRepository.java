package com.seoultech.capstone.roommember.repository;

import com.seoultech.capstone.member.Member;
import com.seoultech.capstone.room.Room;
import com.seoultech.capstone.roommember.RoomMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {

  List<RoomMember> findRoomMembersByMember(Member member);

  List<RoomMember> findRoomMembersByRoom(Room room);

}
