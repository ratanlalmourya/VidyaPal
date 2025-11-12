package com.vidyapal.service;

import com.vidyapal.model.StudyRoom;
import com.vidyapal.repository.StudyRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyRoomService {

    private final StudyRoomRepository studyRoomRepository;

    public StudyRoomService(StudyRoomRepository studyRoomRepository) {
        this.studyRoomRepository = studyRoomRepository;
    }

    public List<StudyRoom> findAll() {
        return studyRoomRepository.findAll();
    }

    public List<StudyRoom> findByOwner(Long userId) {
        return studyRoomRepository.findByOwnerId(userId);
    }

    public Optional<StudyRoom> findById(Long id) {
        return studyRoomRepository.findById(id);
    }
}
