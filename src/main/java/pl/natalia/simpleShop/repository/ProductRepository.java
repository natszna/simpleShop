package pl.natalia.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.natalia.simpleShop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(long productId);
    String findByUserLogin(String login);

}
