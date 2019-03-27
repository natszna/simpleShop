package pl.natalia.simpleShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "products")
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
    //@NotEmpty
    //@JsonIgnore
    //@JoinColumn(name = "order_id", referencedColumnName = "ORDER_ID")
    private Order order;
}
