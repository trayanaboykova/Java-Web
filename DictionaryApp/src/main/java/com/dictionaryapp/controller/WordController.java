package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.service.WordsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private WordsService wordsService;

    public WordController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @GetMapping("/words")
    public String viewAddWord(Model model) {
        model.addAttribute("wordData", new AddWordDTO());
        return "word-add";
    }

    @PostMapping("/words")
    public String doAddWord(
            @Valid AddWordDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordData", data);
            redirectAttributes.addAttribute(
                    "org.springframework.validation.BindingResult.wordData", bindingResult);

            return "redirect:/words";
        }

        wordsService.add(data);

        return "redirect:/home";
    }

    @DeleteMapping("/words/{id}")
    public String deleteWord(@PathVariable String id) {
        // Checked logged in
        wordsService.delete(id);

        return "redirect:/home";
    }

    @GetMapping("/words/fail")
    public String fail() {
        throw new EntityNotFoundException();
    }

    @PostMapping("/home/remove-all")
    public String removeAllWords() {
        // Assuming you have a service class to manage words
        wordsService.removeAllWords();  // This method should delete all words from the database

        // Redirect to the home page or wherever you want to go after removing the words
        return "redirect:/home";
    }


}