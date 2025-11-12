package com.vidyapal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StudyRoom {

    public enum FocusMode {
        GUIDED, SELF_PACED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double completionRate;

    private boolean bookmarked;

    @Enumerated(EnumType.STRING)
    private FocusMode focusMode;

    private LocalDateTime nextSession;

    @ManyToOne
    @JsonBackReference
    private User owner;

    @OneToMany(mappedBy = "studyRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<StudyMaterial> materials = new ArrayList<>();

    @OneToMany(mappedBy = "studyRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Quiz> quizzes = new ArrayList<>();

    public StudyRoom() {
    }

    public StudyRoom(String title, double completionRate, boolean bookmarked, FocusMode focusMode, LocalDateTime nextSession) {
        this.title = title;
        this.completionRate = completionRate;
        this.bookmarked = bookmarked;
        this.focusMode = focusMode;
        this.nextSession = nextSession;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public FocusMode getFocusMode() {
        return focusMode;
    }

    public void setFocusMode(FocusMode focusMode) {
        this.focusMode = focusMode;
    }

    public LocalDateTime getNextSession() {
        return nextSession;
    }

    public void setNextSession(LocalDateTime nextSession) {
        this.nextSession = nextSession;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<StudyMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<StudyMaterial> materials) {
        this.materials = materials;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
