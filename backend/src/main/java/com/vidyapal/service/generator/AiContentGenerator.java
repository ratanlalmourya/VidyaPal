package com.vidyapal.service.generator;

import com.vidyapal.dto.AiTutorResponse;
import com.vidyapal.model.StudyMaterial;
import com.vidyapal.model.StudyRoom;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AiContentGenerator {

    public AiTutorResponse generateResponse(String prompt, StudyRoom context) {
        String summary = "Here's a quick explanation: " + prompt + " can be understood by focusing on its core idea.";
        List<String> keyPoints = new ArrayList<>();
        keyPoints.add("Break the concept into smaller ideas and relate them to examples.");
        keyPoints.add("Use the study material in your room to reinforce the learning.");
        if (context != null && !context.getMaterials().isEmpty()) {
            String materialList = context.getMaterials().stream()
                    .map(StudyMaterial::getTitle)
                    .collect(Collectors.joining(", "));
            keyPoints.add("Relevant materials: " + materialList);
        }

        List<String> followUp = List.of(
                "Can you apply this concept to a real-world scenario?",
                "Which step is hardest for you and why?",
                "What keywords would you search to learn more?"
        );
        return new AiTutorResponse(summary, keyPoints, followUp);
    }
}
