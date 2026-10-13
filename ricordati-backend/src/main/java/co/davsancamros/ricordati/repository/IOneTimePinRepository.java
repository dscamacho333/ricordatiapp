package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.OneTimePin;
import co.davsancamros.ricordati.enums.OneTimePinType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface IOneTimePinRepository extends JpaRepository<OneTimePin, Long> {
    Optional<OneTimePin> findByOtpAndUserAccountEmailAndOneTimePinTypeAndIsUsedFalseAndExpiresAtAfter(
            String otp,
            String email,
            OneTimePinType oneTimePinType,
            LocalDateTime now
    );

}
