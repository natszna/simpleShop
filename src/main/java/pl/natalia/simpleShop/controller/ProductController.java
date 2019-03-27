package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.natalia.simpleShop.model.Product;
import pl.natalia.simpleShop.repository.ProductRepository;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @ModelAttribute("products")
    public List<Product> getAllUsers() {
        return productRepository.findAll();
    }

    @GetMapping("/products")
    public String showList() {
        return "home";
    }
}
