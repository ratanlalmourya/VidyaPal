package com.vidyapal.service;

import com.vidyapal.dto.AiTutorRequest;
import com.vidyapal.dto.AiTutorResponse;
import com.vidyapal.model.StudyRoom;
import com.vidyapal.service.generator.AiContentGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiTutorService {

    private final AiContentGenerator aiContentGenerator;
    private final StudyRoomService studyRoomService;

    public AiTutorService(AiContentGenerator aiContentGenerator, StudyRoomService studyRoomService) {
        this.aiContentGenerator = aiContentGenerator;
        this.studyRoomService = studyRoomService;
    }

    public AiTutorResponse generateResponse(AiTutorRequest request) {
        StudyRoom context = null;
        if (request.getStudyRoomId() != null) {
            context = studyRoomService.findById(request.getStudyRoomId()).orElse(null);
        }
        return aiContentGenerator.generateResponse(request.getPrompt(), context);
    }
}
