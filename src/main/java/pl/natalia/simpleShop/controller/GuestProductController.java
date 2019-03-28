package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.natalia.simpleShop.model.Product;
import pl.natalia.simpleShop.repository.ProductRepository;
import pl.natalia.simpleShop.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
public class GuestProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("role")
    public String currentUserName(Principal principal) {
        if (principal != null) {

            String role =  userRepository.findByLogin(principal.getName()).getRole().toString();
            return role;        }
        return "anonymous";
    }

    @ModelAttribute("products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/home")
    public String showList() {
        return "guest/home";
    }
}
