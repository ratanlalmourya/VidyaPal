package com.vidyapal.repository;

import com.vidyapal.model.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long> {
    List<StudyMaterial> findByStudyRoomId(Long studyRoomId);
}
