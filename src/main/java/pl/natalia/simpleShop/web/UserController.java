package pl.natalia.simpleShop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.UserRepository;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor ste = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, ste);
    }

    @ModelAttribute("role")
    public String currentUserRole(Principal principal) {
        if (principal != null) {
            return userRepository.findByLogin(principal.getName()).getRole().toString();
        }
        return "anonymous";
    }

    @GetMapping("/edit")
    public String editUser(Map<String, Object> model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();

        User user = userRepository.findByLogin(name);
        model.put("user", user);
        return  "user/edit";
    }

    @PostMapping("/edit")
    public String showEditUser(@ModelAttribute("user") User user) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();
        user.setRole(userRepository.findByLogin(name).getRole());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user/products";
    }

}
