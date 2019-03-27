package pl.natalia.simpleShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @NotEmpty
    @Column(name = "address")
    private String address;

    @ManyToOne
    @NotEmpty
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "USER_ID")
    private User user;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", referencedColumnName = "PRODUCT_ID")
    private Product product;

}