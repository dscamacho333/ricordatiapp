package co.davsancamros.ricordati.entity;

import co.davsancamros.ricordati.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false )
    private Long id;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "account_status_id", nullable = false)
    private AccountStatus accountStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToOne(mappedBy = "account")
    private User user;

}
