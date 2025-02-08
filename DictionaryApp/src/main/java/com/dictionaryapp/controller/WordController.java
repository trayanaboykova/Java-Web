package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.service.WordsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private WordsService wordsService;

    public WordController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @GetMapping("/word")
    public String viewAddWord() {
        return "word-add";
    }

    @PostMapping("/word")
    public String doAddWord(@Valid AddWordDto data,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addWordData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWordData", bindingResult);

            return "redirect:/words";
        }


        wordsService.add(data);

        return "redirect:/home";
    }
}
