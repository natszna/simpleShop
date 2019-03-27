package pl.natalia.simpleShop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
public enum Role{
    ADMIN,
    USER
}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @NotEmpty
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @NotEmpty
    @Column(name="approved")
    private boolean approved;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public User() {
    }


}
