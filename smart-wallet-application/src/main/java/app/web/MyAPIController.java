package app.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MyAPIController {

    @GetMapping("/info/1")
    public String getInfo1(HttpServletRequest request, HttpServletResponse response) {

        String author = request.getHeader("Author");
        response.addHeader("Application-Name", "Spring Boot App, Smart Wallet!");

        Cookie cookie1 = new Cookie("color", "Pink");
        Cookie cookie2 = new Cookie("city", "Plovdiv");
        Cookie cookie3 = new Cookie("country", "Bulgaria");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);

        return "Hello, " + author + "!";
    }

    @GetMapping("/info/2")
    public String getInfo2(@CookieValue(value = "color", defaultValue = "Pink") String color, @CookieValue(value = "city", defaultValue = "Plovdiv") String city, @CookieValue(value = "country", defaultValue = "Bulgaria") String country) {

        Cookie cookie1 = new Cookie("sessionId", "jklhdasjhklsadhjklsad");

        return color + " " + city + " " + country;
    }
}
