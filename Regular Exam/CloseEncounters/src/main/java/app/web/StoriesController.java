package app.web;

import app.story.service.StoryService;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.StoryRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/stories")
public class StoriesController {

    private final StoryService storyService;
    private final UserService userService;

    @Autowired
    public StoriesController(StoryService storyService, UserService userService) {
        this.storyService = storyService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public ModelAndView showAddStoryRequest(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-story");
        modelAndView.addObject("user", user);
        modelAndView.addObject("storyRequest", new StoryRequest());

        return modelAndView;
    }

    @PostMapping
    public String addStory(@Valid StoryRequest storyRequest, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "add-story";
        }

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        storyService.addStory(storyRequest, user);

        return "redirect:/home";
    }

    @PostMapping("/shareStory")
    public String shareStory(@RequestParam("storyId") UUID storyId) {
        storyService.shareStory(storyId);
        return "redirect:/home";
    }

}
