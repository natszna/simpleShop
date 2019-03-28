package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Controller
public class GuestController {
    @Autowired
    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor ste = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, ste);
    }

    @ModelAttribute("guest")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/registry")
    public String registryUser(Map<String, Object> model) {
        model.put("user", new User());
        model.put("roles", User.Role.values());
        return "guest/registry";
    }

    @PostMapping("/registry")
    public String showRegistryUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

}
