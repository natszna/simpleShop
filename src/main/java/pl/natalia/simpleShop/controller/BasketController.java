package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.natalia.simpleShop.model.Product;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.ProductRepository;
import pl.natalia.simpleShop.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("basket")
public class BasketController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("basket")
    public Set<Product> addProductToBasket() {
        return new HashSet<>();
    }

    @GetMapping("/products/add/{id}")
    public String showAddProductToBasket(@SessionAttribute("basket") Set<Product> basket, @PathVariable("id") Long productId) {
        boolean userVerification = showAuthentication().getLogin().equals(productRepository.findByProductId(productId).getUser().getLogin());
        if (userVerification == false){
        basket.add(productRepository.findOne(productId));
        }
        return "redirect:/full";
    }

    public User showAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();
        return userRepository.findByLogin(name);
    }

    @ModelAttribute("basket")
    public List<Product> getAllProductsFromBasket() {
        return productRepository.findAll();
    }

    @GetMapping("/basket")
    public String showListFromBasket() {
        return "product/basket";
    }

    @GetMapping("/basket/delete/{id}")
    public String deleteProduct(@SessionAttribute("basket") Set<Product> basket, @PathVariable("id") Long productId) {
        basket.remove(productRepository.findOne(productId));
        return "redirect:/basket";
    }

    @ModelAttribute("products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products")
    public String showList() {
        return "home";
    }

    @ModelAttribute("full")
    public List<Product> getAllProductsInf() {

        return productRepository.findAll();
    }

    @GetMapping("/full")
    public String showAllProducts() {
        return "product/products";
    }


}
