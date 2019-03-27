package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users")
    public String showList() {
        return "user/list";
    }

    @GetMapping("/users/edit/{id}")
    public String showEdit(Map<String, Object> model, @PathVariable("id") long userId) {
        User u = userRepository.findByUserId(userId);
        model.put("user", u);
        model.put("roles", User.Role.values());
        return "user/add";
    }

    @PutMapping("/users/edit/{id}")
    public String showList2(@ModelAttribute("user") User user, @PathVariable("id") long userId) {
        userRepository.findByUserId(userId);
        return "redirect:/users";
    }


    @GetMapping("/users/add")
    public String showAdd(Map<String, Object> model) {
        model.put("user", new User());
        model.put("roles", User.Role.values());
        return "user/add";
    }

    @PostMapping("/users/add")
    public String add(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userRepository.delete(id);
        return "redirect:/users";
    }


    @GetMapping("/users/approved/{id}")
    public String approved(@PathVariable("id") Long id) {
        User user = userRepository.findByUserId(id);
        user.setApproved(true);
        userRepository.save(user);
        return "redirect:/users";
    }


}
