package io.teamchallenge.entity;

import io.teamchallenge.entity.cartitem.CartItem;
import io.teamchallenge.enumerated.Role;
import io.teamchallenge.enumerated.Sex;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"address", "orders", "cartItems"})
@EqualsAndHashCode(exclude = {"address", "orders", "cartItems"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "secondary_phone_number")
    private String secondaryPhoneNumber;

    @Column(name = "birthdate")
    private Date birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_orders",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<Order> orders = new HashSet<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlists",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> wishlists = new HashSet<>();


    @Setter(AccessLevel.PRIVATE)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "refresh_token_key", nullable = false)
    private String refreshTokenKey;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    /**
     * Adds a cart item to the user's list of cart items.
     * Also sets the user for the added cart item.
     *
     * @param cartItem The cart item to be added.
     */
    public void addCarItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setUser(this);
    }

    /**
     * Removes a cart item from the user's list of cart items.
     * Also sets the user of the removed cart item to null.
     *
     * @param cartItem The cart item to be removed.
     */
    public void removeCarItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setUser(null);
    }

    /**
     * Adds an order to the user's list of orders.
     *
     * @param order The order to be added.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Removes an order from the user's list of orders.
     *
     * @param order The order to be removed.
     */
    public void removeOrder(Order order) {
        orders.remove(order);
    }
}
