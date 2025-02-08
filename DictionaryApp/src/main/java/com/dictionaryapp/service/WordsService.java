package com.dictionaryapp.service;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class WordsService {

    private final LanguageRepository languageRepository;
    private final UserSession userSession;
    private final UserRepository userRepository;

    public WordsService(LanguageRepository languageRepository, UserSession userSession, UserRepository userRepository) {
        this.languageRepository = languageRepository;
        this.userSession = userSession;
        this.userRepository = userRepository;
    }

    public void add(AddWordDto data) {
        Word word = new Word();

        // TODO: Save and attach correct user + language
    }
}
