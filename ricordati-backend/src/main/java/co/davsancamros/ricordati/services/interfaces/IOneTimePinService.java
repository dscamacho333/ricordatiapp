package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.request.create.OneTimePinRegistrationValidationRequestDTO;
import co.davsancamros.ricordati.entity.OneTimePin;
import co.davsancamros.ricordati.enums.OneTimePinType;

public interface IOneTimePinService {

    OneTimePin create(Long userId, OneTimePinType oneTimePinType);
    void validateOTP(OneTimePinRegistrationValidationRequestDTO oneTimePinRegistrationValidationRequestDTO, OneTimePinType oneTimePinType);

}
