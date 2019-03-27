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
import java.util.Map;
import java.util.Set;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("userProducts")
    public List<Product> getAllUsers(Authentication authentication) {
        final String name = authentication.getName();
        return productRepository.findByUserLogin(name);
    }

    @GetMapping("/userProducts")
    public String showList() {
        return "product/userProducts";
    }

    @GetMapping("/userProducts/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (productRepository.findByProductId(id).isAvailable() == true){
            productRepository.delete(id);}
        return "redirect:/userProducts";
    }

    @GetMapping("/userProducts/addProduct")
    public String showAddProduct(Map<String, Object> model2) {
        model2.put("product", new Product());
        //   model.put("roles", User.Role.values());
        return "product/addProduct";
    }

    @PostMapping("/userProducts/addProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();
        final User user = userRepository.findByLogin(name);
        product.setUser(user);
        productRepository.save(product);
        return "redirect:/userProducts";
    }

    @GetMapping("/userProducts/edit/{id}")
    public String showEditProduct(Map<String, Object> model2, @PathVariable("id") long productId) {
        Product product = productRepository.findByProductId(productId);
        if (product.isAvailable() == true) {
            model2.put("product", product);}

        return "product/addProduct";
    }

    @PutMapping("/userProducts/edit/{id}")
    public String showList2(@ModelAttribute("product") Product product, @PathVariable("id") long productId) {
        productRepository.findByProductId(productId);
        return "redirect:/userProducts";
    }
}
