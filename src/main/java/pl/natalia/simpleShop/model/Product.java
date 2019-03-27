package pl.natalia.simpleShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;

    @NotEmpty
    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @NotEmpty
    @Column(name = "price")
    private double price;

    @ManyToOne
    @NotEmpty
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "USER_ID")
    private User user;

    @NotEmpty
    @Column(name="available")
    private boolean available;

    @OneToOne(mappedBy = "product")
    private Order order;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
