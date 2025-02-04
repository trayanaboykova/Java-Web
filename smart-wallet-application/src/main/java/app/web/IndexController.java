package app.web;

import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.RegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping
public class IndexController {

    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    //когато искаме да изпращаме модел атрибути
//    @GetMapping("/")
//    public ModelAndView getIndexPage(){
//       return new ModelAndView("index");
//    }

    // когато искаме просто да върнем view
    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());


        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerNewUser(RegisterRequest registerRequest) {

        User registeredUser = userService.register(registerRequest);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        modelAndView.addObject("user", registeredUser);

        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView getHomePage() {

        User user = userService.getById(UUID.fromString("3467407c-9663-4fc7-a771-9acb7a6f721f")); //TODO: remove hardcoding

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

}
