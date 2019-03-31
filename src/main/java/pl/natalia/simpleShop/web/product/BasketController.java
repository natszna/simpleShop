package pl.natalia.simpleShop.web.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.natalia.simpleShop.model.Order;
import pl.natalia.simpleShop.model.Product;
import pl.natalia.simpleShop.model.User;
import pl.natalia.simpleShop.repository.OrderRepository;
import pl.natalia.simpleShop.repository.ProductRepository;
import pl.natalia.simpleShop.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@SessionAttributes("basket")
@RequestMapping("/user")
public class BasketController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @ModelAttribute("role")
    public String currentUserRole(Principal principal) {
        if (principal != null) {
            return userRepository.findByLogin(principal.getName()).getRole().toString();
        }
        return "anonymous";
    }

    @ModelAttribute("login")
    public String currentLogin(Principal principal) {
        if (principal != null) {
            return principal.getName();
        }
        return "anonymous";
    }

    @ModelAttribute("basket")
    public Set<Product> addProductToBasket() {
        return new HashSet<>();
    }

    @GetMapping("/products/add/{id}")
    public String showAddProductToBasket(@SessionAttribute("basket") Set<Product> basket, @PathVariable("id") Long productId) {
        boolean userVerification = showAuthentication().getLogin().equals(productRepository.findByProductId(productId).getUser().getLogin());
        if (userVerification == false) {
            basket.add(productRepository.findOne(productId));
        }
        return "redirect:/user/products";
    }

    public User showAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();
        return userRepository.findByLogin(name);
    }

    @GetMapping("/basket")
    public String showListFromBasket() {
        return "user/basket";
    }

    @GetMapping("/basket/delete/{id}")
    public String deleteProductFromBasket(@SessionAttribute("basket") Set<Product> basket, @PathVariable("id") Long productId) {
        basket.remove(productRepository.findOne(productId));
        return "redirect:/user/basket";
    }
    @ModelAttribute("products")
    public List<Product> getAllProductsInf() {
        return productRepository.findAll();
    }

    @GetMapping("/products")
    public String showAllProducts() {
        return "user/products";
    }

    @GetMapping("/orderForm")
    public String addOrder(Map<String, Object> model) {
        model.put("order", new Order());
        return "user/orderForm";
    }

    @PostMapping("/orderForm")
    public String showAddOrder(@Valid @ModelAttribute("order") Order order, BindingResult result,
                               @SessionAttribute("basket") Set<Product> basket) {
        if (result.hasErrors()){
            return "user/orderForm";
        }
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String name = authentication.getName();
        final User user = userRepository.findByLogin(name);
        order.setUser(user);
        order.setProducts(new ArrayList<>(basket));
        for (Product product :
                new ArrayList<>(basket) ) {
            product.setAvailable(false);
            productRepository.save(product);
        }
        basket.clear();

        orderRepository.save(order);
        return "redirect:/user/userOrder";
    }

}
