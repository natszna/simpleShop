package pl.natalia.simpleShop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.natalia.simpleShop.model.Product;
import pl.natalia.simpleShop.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestGuestController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/home")
    public List<Product> listAllProducts() {
        return productRepository.findAllByAvailable(true);
    }

}
