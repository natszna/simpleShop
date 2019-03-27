package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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

    @ModelAttribute("userName")
    public String currentUserName(Principal principal) {
        if (principal != null) {
            return principal.getName();
        }
        return "anonymous";
    }


    @ModelAttribute("users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users")
    public String showUsersList() {
        return "user/list";
    }

    @GetMapping("/users/add")
    public String addUser(Map<String, Object> model) {
        model.put("user", new User());
        model.put("roles", User.Role.values());
        return "user/add";
    }

    @PostMapping("/users/add")
    public String showAddUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(Map<String, Object> model, @PathVariable("id") long userId) {
        User user = userRepository.findByUserId(userId);
        model.put("user", user);
        model.put("roles", User.Role.values());
        return "user/add";
    }

    @PutMapping("/users/edit/{id}")
    public String showEditUser(@ModelAttribute("user") User user, @PathVariable("id") long userId) {
        userRepository.findByUserId(userId);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        userRepository.delete(userId);
        return "redirect:/users";
    }


    @GetMapping("/users/approved/{id}")
    public String approveUser(@PathVariable("id") Long userId) {
        User user = userRepository.findByUserId(userId);
        user.setApproved(true);
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }


    @GetMapping("/registry")
    public String registryUser(Map<String, Object> model) {
        model.put("user", new User());
        model.put("roles", User.Role.values());
        return "user/registry";
    }

    @PostMapping("/registry")
    public String showRegistryUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

}
