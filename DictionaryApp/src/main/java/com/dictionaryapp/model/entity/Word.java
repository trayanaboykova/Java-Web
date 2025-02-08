package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    private String example;

    @Column(nullable = false)
    private Instant inputDate;

    @ManyToOne(optional = false)
    private Language language;

    @ManyToOne(optional = false)
    private User addedBy;

    public Word() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Instant getInputDate() {
        return inputDate;
    }

    public void setInputDate(Instant inputDate) {
        this.inputDate = inputDate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
