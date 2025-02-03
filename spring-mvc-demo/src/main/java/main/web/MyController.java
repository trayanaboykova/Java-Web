package main.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

// Handler Method is a method that handles HTTP requests

@RestController
@RequestMapping("/info") // base path for all handler methods
public class MyController {

    // HTTP GET /info/time-now
    // 1. Колко е часът сега?
    @GetMapping("/time-now")
    public String getTimeNow() {

        return "Time now is " + LocalTime.now();
    }

    // HTTP GET /info/today
    // 2. Ден от седмицата?
    @GetMapping("/today")
    public String getDayOfWeek() {

        return "Today is " + LocalDateTime.now().getDayOfWeek().name();
    }

}
