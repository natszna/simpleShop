package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor ste = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, ste);
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }


    @GetMapping("/user/edit")
    public String editMysefl(Map<String, Object> model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();

        User user = userRepository.findByLogin(name);
        model.put("user", user);
        model.put("roles", User.Role.values());
        return "user/add";
    }

    @PutMapping("/user/edit/{id}")
    public String showEditMyself(@ModelAttribute("user") User user, @PathVariable("id") long userId) {
        userRepository.findByUserId(userId);
        return "redirect:/full";
    }
}
