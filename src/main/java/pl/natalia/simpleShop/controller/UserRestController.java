package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.UserRepository;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users_list")
    public List<User> usersList() {
        return userRepository.findAll();
    }
}
