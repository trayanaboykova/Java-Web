package com.dictionaryapp.service;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordsService {

    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final WordRepository wordRepository;

    public WordsService(LanguageRepository languageRepository, UserRepository userRepository, UserSession userSession, WordRepository wordRepository) {
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.wordRepository = wordRepository;
    }

    public void add(AddWordDTO data) {
        if (!userSession.isUserLoggedIn()) {
            throw new IllegalStateException("User not logged in");
        }

        // Fetch the user from the session
        Optional<User> userOptional = userRepository.findById(userSession.userId());
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("User not found in database");
        }
        User user = userOptional.get();

        // Ensure the language is selected and valid
        if (data.getLanguage() == null || data.getLanguage().isEmpty()) {
            throw new IllegalArgumentException("Language must be selected");
        }

        Language language = languageRepository.findByLanguageName(LanguageEnum.valueOf(data.getLanguage()));
        if (language == null) {
            throw new IllegalArgumentException("Invalid language selected");
        }

        Word word = new Word();
        word.setTerm(data.getTerm());
        word.setTranslation(data.getTranslation());
        word.setExample(data.getExample());
        word.setDate(data.getInputDate());
        word.setAddedBy(user);
        word.setLanguage(language);

        wordRepository.save(word);
    }

    public List<Word> findSpanish() {
        Optional<User> user = userRepository.findById(userSession.userId());

        if (user.isEmpty()) {
            return new ArrayList<>();
        }

        Language language = languageRepository.findByLanguageName(LanguageEnum.SPANISH);

        return wordRepository.findByLanguageAndAddedBy(language, user.get());
    }

    public List<Word> findGerman() {
        Optional<User> user = userRepository.findById(userSession.userId());

        if (user.isEmpty()) {
            return new ArrayList<>();
        }

        Language language = languageRepository.findByLanguageName(LanguageEnum.GERMAN);

        return wordRepository.findByLanguageAndAddedBy(language, user.get());
    }

    public List<Word> findFrench() {
        Optional<User> user = userRepository.findById(userSession.userId());

        if (user.isEmpty()) {
            return new ArrayList<>();
        }

        Language language = languageRepository.findByLanguageName(LanguageEnum.FRENCH);

        return wordRepository.findByLanguageAndAddedBy(language, user.get());
    }

    public List<Word> findItalian() {
        Optional<User> user = userRepository.findById(userSession.userId());

        if (user.isEmpty()) {
            return new ArrayList<>();
        }

        Language language = languageRepository.findByLanguageName(LanguageEnum.ITALIAN);

        return wordRepository.findByLanguageAndAddedBy(language, user.get());
    }

    public void delete(String id) {
        userRepository.findById(userSession.userId())
                .flatMap(user -> wordRepository.findByIdAndAddedBy(id, user))
                .ifPresent(wordRepository::delete);

//        Optional<User> user = userRepository.findById(userSession.userId());
//
//        if (user.isEmpty()) {
//            return;
//        }
//
//        Optional<Word> maybeWord = wordRepository.findByIdAndAddedBy(id, user.get());
//
//        if (maybeWord.isEmpty()) {
//            return;
//        }
//
//        wordRepository.delete(maybeWord.get());
    }

    public void removeAllWords() {
        wordRepository.deleteAll();  // Deletes all records in the words table
    }
}