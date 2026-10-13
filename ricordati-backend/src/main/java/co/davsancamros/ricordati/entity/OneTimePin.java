package co.davsancamros.ricordati.entity;


import co.davsancamros.ricordati.enums.OneTimePinType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ONE_TIME_PIN")
@NoArgsConstructor
@Getter
@Setter
public class OneTimePin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "otp", length = 6, nullable = false)
    private String otp;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private OneTimePinType oneTimePinType;

    @Column(name = "expires_At", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "is_used", nullable = false)
    private Boolean isUsed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
