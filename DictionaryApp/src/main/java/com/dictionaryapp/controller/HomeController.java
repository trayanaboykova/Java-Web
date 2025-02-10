package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.WordsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final WordsService wordsService;

    public HomeController(UserSession userSession, WordsService wordsService) {
        this.userSession = userSession;
        this.wordsService = wordsService;
    }

    @GetMapping("/")
    public String notLogged() {
        if (userSession.isUserLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }


    @GetMapping("/home")
    public String logged(Model model) {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        List<Word> germanWords = wordsService.findGerman();
        List<Word> spanishWords = wordsService.findSpanish();
        List<Word> frenchWords = wordsService.findFrench();
        List<Word> italianWords = wordsService.findItalian();

        model.addAttribute("germanWords", germanWords);
        model.addAttribute("spanishWords", spanishWords);
        model.addAttribute("frenchWords", frenchWords);
        model.addAttribute("italianWords", italianWords);

        return "home";
    }

    @GetMapping("/fail/{id}")
    public String fail(@PathVariable Long id) {
        if (id % 2 == 0) {
            throw new EntityNotFoundException();
        }

        return "redirect:/home";
    }

}