package iuh.fit.se.controllers;

import iuh.fit.se.entities.Role;
import iuh.fit.se.entities.User;
import iuh.fit.se.repositories.RoleRepository;
import iuh.fit.se.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String getUsers(
            Model model,
            @Param("keyword") String keyword,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    ) {
        List<User> users = new ArrayList<>();

        if (keyword != null && !keyword.isEmpty()) {
            users.addAll(userService.findByKeyword(keyword));
        } else if (!startDate.isEmpty() && !endDate.isEmpty()) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            users.addAll(userService.findByDobBetween(start, end));
        } else {
            users.addAll(userService.findAll());
        }

        model.addAttribute("users", users);
        return "user/list";
    }

    @PostMapping("/save")
    public String registration(@Valid @ModelAttribute User user,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findByEmail(user.getEmail());

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "user/form";
        }

        if (existingUser != null && !existingUser.getId().equals(user.getId())) {
            result.rejectValue("email", "403", "Email da ton tai");
            return "user/form";
        }

        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String showForm(Model model, @PathVariable Integer id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);

        List<String> listFavoriteLanguage = List.of("Java", "Python", "C++", "C#", "JavaScript");
        model.addAttribute("listFavoriteLanguage", listFavoriteLanguage);

        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);

        return "user/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
