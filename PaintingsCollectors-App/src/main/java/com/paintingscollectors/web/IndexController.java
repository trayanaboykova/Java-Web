package com.paintingscollectors.web;

import com.paintingscollectors.painting.model.Painting;
import com.paintingscollectors.painting.service.PaintingService;
import com.paintingscollectors.user.model.User;
import com.paintingscollectors.user.service.UserService;
import com.paintingscollectors.web.dto.LoginRequest;
import com.paintingscollectors.web.dto.RegisterRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {

    private final UserService userService;
    private final PaintingService paintingService;

    @Autowired
    public IndexController(UserService userService, PaintingService paintingService) {
        this.userService = userService;
        this.paintingService = paintingService;
    }

    @GetMapping("/")
    public String getIndexPage() {

        return "index";
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());

        return modelAndView;
    }

    // Binding -> процес по намапване на JSON обект към Java клас
    @PostMapping("/register")
    public String processRegisterRequest(@Valid RegisterRequest registerRequest, BindingResult bindingResult) {

        // First, check if the user already exists.
        if (userService.existsByUsernameOrEmail(registerRequest.getUsername(), registerRequest.getEmail())) {
            bindingResult.reject("registrationError", "User with this email or username already exists.");
        }

        if (bindingResult.hasErrors() || !registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            return "register";
        }

        userService.registerUser(registerRequest);

        // ВАЖНО!!!
        // Когато се логвам - активирам сесия и поставям в тази сесия ID-то на потребителя!!!!

        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginRequest", new LoginRequest());

        return modelAndView;
    }

    @PostMapping("/login")
    public String processLoginRequest(@Valid LoginRequest loginRequest, BindingResult bindingResult, HttpSession session) {

        // First, check if the user already exists.
        if (!userService.existsByUsername(loginRequest.getUsername())) {
            bindingResult.reject("loginError", "User with this email or username already exists.");
        }

        if (bindingResult.hasErrors()) {
            return "login";
        }

        // Допълнителна валидация при грешно потребителско име и правилна парола и обратното
        try {
            User user = userService.loginUser(loginRequest);
            session.setAttribute("user_id", user.getId());
            return "redirect:/home";
        } catch (RuntimeException e) {
            bindingResult.reject("loginError", e.getMessage());
            return "login";
        }

        // ВАЖНО!!!
        // Когато се логвам - активирам сесия и поставям в тази сесия ID-то на потребителя!!!!
        // session.setAttribute("user_id", user.getId());

    }

    @GetMapping("/home")
    public ModelAndView getHomePage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        List<Painting> allSystemPaintings = paintingService.getAllPaintings();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user", user);
        modelAndView.addObject("allSystemPaintings", allSystemPaintings);

        return modelAndView;
    }

    @GetMapping("/logout")
    public String getLogoutPage(HttpSession session) {

        // ВАЖНО:
        // Прекратяваме сесията и редиректваме към индекс или логин
        session.invalidate();

        return "redirect:/";
    }
}