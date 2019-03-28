package pl.natalia.simpleShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

    @NotNull
    @DecimalMin(value = "0.1")
    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "USER_ID")
    private User user;

    @Column(name="available")
    private boolean available;

    //    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Column(name = "add_date")
    @Temporal(TemporalType.DATE)
    private Date date;

//    @ManyToOne(mappedBy = "products")
//    private Order order;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                available == product.available &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, description, price, available);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", available=" + available +
                '}';
    }
}
