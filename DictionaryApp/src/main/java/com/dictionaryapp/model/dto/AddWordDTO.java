package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddWordDTO {

    @NotNull(message = "Term is required")
    @Size(min = 1, message = "Term cannot be empty")
    private String term;

    @NotNull(message = "Translation is required")
    @Size(min = 1, message = "Translation cannot be empty")
    private String translation;

    private String example;

    @NotNull(message = "Input date is required")
    private LocalDate inputDate;

    @NotNull(message = "Language is required")
    private String language;

    public AddWordDTO() {}

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

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}