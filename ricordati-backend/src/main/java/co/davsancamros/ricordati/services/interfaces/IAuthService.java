package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.request.create.LoginRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinRegistrationValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinResetPasswordValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.create.ResetPasswordRequestDTO;
import co.davsancamros.ricordati.dto.response.LoginResponseDTO;

public interface IAuthService {

    LoginResponseDTO login(LoginRequestDTO request);
    void validateRegistrationOTP(OneTimePinRegistrationValidationRequestDTO oneTimePinRegistrationValidationRequestDTO);
    void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO);
    void validateResetPasswordOTP(OneTimePinResetPasswordValidationRequestDTO oneTimePinResetPasswordValidationRequestDTO);

}
