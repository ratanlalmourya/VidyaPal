package com.vidyapal.repository;

import com.vidyapal.model.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyRoomRepository extends JpaRepository<StudyRoom, Long> {
    List<StudyRoom> findByOwnerId(Long ownerId);
}
