package pl.natalia.simpleShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.natalia.simpleShop.model.Product;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.ProductRepository;
import pl.natalia.simpleShop.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor ste = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, ste);
    }

    @ModelAttribute("role")
    public String currentUserName(Principal principal) {
        if (principal != null) {
            return userRepository.findByLogin(principal.getName()).getRole().toString();
        }
        return "anonymous";
    }


    @ModelAttribute("userProducts")
    public List<Product> getAllProducts(Authentication authentication) {
        final String name = authentication.getName();
        return productRepository.findByUserLogin(name);
    }

    @GetMapping("/userProducts")
    public String showUserProducts() {
        return "user/userProducts";
    }

    @GetMapping("/userProducts/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        if (productRepository.findByProductId(productId).isAvailable() == true){
            productRepository.delete(productId);}
        return "redirect:/user/userProducts";
    }

    @GetMapping("/userProducts/addProduct")
    public String addProduct(Map<String, Object> model2) {
        model2.put("product", new Product());
        return "user/addProduct";
    }

    @PostMapping("/userProducts/addProduct")
    public String showAddProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Errors errors ) {
        if (result.hasErrors()){

            return "user/addProduct";
        }
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();
        final User user = userRepository.findByLogin(name);
        product.setDate(new Date());
        product.setUser(user);
        productRepository.save(product);
        return "redirect:/user/userProducts";
    }

    @GetMapping("/userProducts/edit/{id}")
    public String editProduct(Map<String, Object> model2, @PathVariable("id") long productId) {
        Product product = productRepository.findByProductId(productId);
        if (product.isAvailable() == true) {
            model2.put("product", product);
        }
        return "user/addProduct";
    }

    @PutMapping("/userProducts/edit/{id}")
        public String showEditProduct(@ModelAttribute("product") Product product, @PathVariable("id") long productId) {
            productRepository.findByProductId(productId);
            return "redirect:/user/userProducts";
    }
}
