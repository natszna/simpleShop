package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;


@Controller
public class AdminController {
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

    @ModelAttribute("admin")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/admin")
    public String showUsersList() {
        return "admin/list";
    }

    @GetMapping("/admin/add")
    public String addUser(Map<String, Object> model) {
        model.put("user", new User());
        model.put("roles", User.Role.values());
        return "admin/add";
    }

    @PostMapping("/admin/add")
    public String showAddUser(@Valid @ModelAttribute("user") User user,
                              BindingResult result, Errors errors) {
        if (result.hasErrors()){
            return "admin/add";
        }    if (userRepository.findByLogin(user.getEmail()) != null) {

            errors.rejectValue("email", "pl.natalia.simpleShop.user.Unique.message");
            return "admin/add";
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {

            errors.rejectValue("login", "pl.natalia.simpleShop.user.Unique.message");
            return "admin/add";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(Map<String, Object> model, @PathVariable("id") long userId) {
        User user = userRepository.findByUserId(userId);
        model.put("user", user);
        model.put("roles", User.Role.values());
        return "admin/add";
    }

    @PutMapping("/admin/edit/{id}")
    public String showEditUser(@ModelAttribute("user") User user, @PathVariable("id") long userId) {
        userRepository.findByUserId(userId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String DeleteUser(@PathVariable("id") Long userId) {
        userRepository.delete(userId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/approved/{id}")
    public String approveUser(@PathVariable("id") Long userId) {
        User user = userRepository.findByUserId(userId);
        user.setApproved(true);
        userRepository.save(user);
        return "redirect:/admin";
    }


}
