package iuh.fit.se.controllers;

import iuh.fit.se.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        user.setGender("Male");
        model.addAttribute("user", user);

        List<String> listFavoriteLanguage = List.of("Java", "Python", "C++", "C#", "JavaScript");
        model.addAttribute("listFavoriteLanguage", listFavoriteLanguage);

        return "user/form";
    }
}
