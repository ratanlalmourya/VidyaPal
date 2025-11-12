package com.vidyapal;

import com.vidyapal.model.Notification;
import com.vidyapal.model.Quiz;
import com.vidyapal.model.StudyMaterial;
import com.vidyapal.model.StudyRoom;
import com.vidyapal.model.User;
import com.vidyapal.repository.NotificationRepository;
import com.vidyapal.repository.QuizRepository;
import com.vidyapal.repository.StudyMaterialRepository;
import com.vidyapal.repository.StudyRoomRepository;
import com.vidyapal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final StudyRoomRepository studyRoomRepository;
    private final StudyMaterialRepository studyMaterialRepository;
    private final QuizRepository quizRepository;
    private final NotificationRepository notificationRepository;

    public DataInitializer(UserRepository userRepository,
                           StudyRoomRepository studyRoomRepository,
                           StudyMaterialRepository studyMaterialRepository,
                           QuizRepository quizRepository,
                           NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.studyRoomRepository = studyRoomRepository;
        this.studyMaterialRepository = studyMaterialRepository;
        this.quizRepository = quizRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) {
        User priya = new User("Priya Sharma", "priya@vidyapal.ai", "password", "Crack the GATE exam", "https://i.pravatar.cc/150?img=47");
        User arjun = new User("Arjun Patel", "arjun@vidyapal.ai", "password", "Master full-stack development", "https://i.pravatar.cc/150?img=12");
        userRepository.saveAll(List.of(priya, arjun));

        StudyRoom gateMath = new StudyRoom("GATE Engineering Maths", 0.72, true, StudyRoom.FocusMode.GUIDED,
                LocalDateTime.now().plusDays(1));
        gateMath.setOwner(priya);
        StudyRoom osRevision = new StudyRoom("Operating Systems", 0.54, false, StudyRoom.FocusMode.SELF_PACED,
                LocalDateTime.now().plusHours(5));
        osRevision.setOwner(priya);
        StudyRoom reactBootcamp = new StudyRoom("React Native Bootcamp", 0.3, true, StudyRoom.FocusMode.GUIDED,
                LocalDateTime.now().plusDays(2));
        reactBootcamp.setOwner(arjun);
        studyRoomRepository.saveAll(List.of(gateMath, osRevision, reactBootcamp));

        StudyMaterial laplaceNotes = new StudyMaterial("Laplace Transform Cheatsheet",
                "Step-by-step solved examples and summaries", "https://vidyapal.ai/materials/laplace",
                "Laplace transforms convert differential equations into algebraic equations...",
                StudyMaterial.MaterialType.DOCUMENT);
        laplaceNotes.setStudyRoom(gateMath);
        StudyMaterial laplaceVideo = new StudyMaterial("Laplace Transform Explained",
                "15 minute visual explanation with mnemonics", "https://youtu.be/dummy-laplace",
                null, StudyMaterial.MaterialType.VIDEO);
        laplaceVideo.setStudyRoom(gateMath);
        StudyMaterial osFlashcards = new StudyMaterial("OS Scheduling Flashcards",
                "Quick revision cards for CPU scheduling algorithms", null,
                "FCFS - simple but can starve long jobs...", StudyMaterial.MaterialType.FLASHCARD);
        osFlashcards.setStudyRoom(osRevision);
        studyMaterialRepository.saveAll(List.of(laplaceNotes, laplaceVideo, osFlashcards));

        Quiz laplaceQuiz = new Quiz("What is the Laplace transform of 1?", 0,
                List.of("1/s", "s", "e^s", "ln(s)"));
        laplaceQuiz.setStudyRoom(gateMath);
        Quiz osQuiz = new Quiz("Which scheduling algorithm is preemptive?", 1,
                List.of("FCFS", "Round Robin", "SJF", "Priority"));
        osQuiz.setStudyRoom(osRevision);
        quizRepository.saveAll(List.of(laplaceQuiz, osQuiz));

        Notification n1 = new Notification("AI Tutor Summary Ready",
                "Your Laplace Transform summary is ready to review.", LocalDateTime.now().minusHours(1), false);
        n1.setUser(priya);
        Notification n2 = new Notification("Quiz Reminder",
                "A quick OS scheduling quiz awaits in your Operating Systems room.", LocalDateTime.now().minusMinutes(30), false);
        n2.setUser(priya);
        Notification n3 = new Notification("Study Room milestone",
                "You're 30% through the React Native Bootcamp!", LocalDateTime.now().minusDays(1), true);
        n3.setUser(arjun);
        notificationRepository.saveAll(List.of(n1, n2, n3));
    }
}
