package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.request.create.OneTimePinRegistrationValidationRequestDTO;
import co.davsancamros.ricordati.entity.OneTimePin;
import co.davsancamros.ricordati.entity.User;
import co.davsancamros.ricordati.enums.OneTimePinType;
import co.davsancamros.ricordati.repository.IOneTimePinRepository;
import co.davsancamros.ricordati.services.interfaces.IAccountService;
import co.davsancamros.ricordati.services.interfaces.IOneTimePinService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OneTimePinServiceImplementation implements IOneTimePinService {

    private final IOneTimePinRepository oneTimePinRepository;
    private final IAccountService accountService;

    @Override
    @Transactional
    public OneTimePin create(Long userId, OneTimePinType oneTimePinType) {


        OneTimePin oneTimePin = new OneTimePin();

        oneTimePin.setOtp(
                generateSixDigitsPin()
        );

        oneTimePin.setOneTimePinType(
                oneTimePinType
        );

        oneTimePin.setExpiresAt(
                LocalDateTime.now().plusHours(23).plusMinutes(59).plusSeconds(59)
        );

        oneTimePin.setIsUsed(
                Boolean.FALSE
        );

        User userEntity = new User();

        userEntity.setId(userId);

        oneTimePin.setUser(
                userEntity
        );

        this.oneTimePinRepository
                .save(oneTimePin);

        return oneTimePin;
    }

    @Override
    @Transactional
    public void validateOTP(OneTimePinRegistrationValidationRequestDTO oneTimePinRegistrationValidationRequestDTO, OneTimePinType oneTimePinType) {

        OneTimePin oneTimePinEntity = this.oneTimePinRepository
                .findByOtpAndUserAccountEmailAndOneTimePinTypeAndIsUsedFalseAndExpiresAtAfter(
                        oneTimePinRegistrationValidationRequestDTO.getOtp(),
                        oneTimePinRegistrationValidationRequestDTO.getEmail(),
                        oneTimePinType,
                        LocalDateTime.now()
                )
                .orElseThrow(
                        () ->  new EntityNotFoundException("OTP no es válido")
                );

        oneTimePinEntity.setIsUsed(Boolean.TRUE);

        this.oneTimePinRepository
                        .save(oneTimePinEntity);

    }

    private String generateSixDigitsPin(){

        SecureRandom secureRandom = new SecureRandom();

        int otp = secureRandom.nextInt(100000);

        return String.format(
                "%06d",
                otp
        );
    }

}
