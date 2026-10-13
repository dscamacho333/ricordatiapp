package co.davsancamros.ricordati.controller.implementation;

import co.davsancamros.ricordati.controller.interfaces.IAuthController;
import co.davsancamros.ricordati.dto.request.create.LoginRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinRegistrationValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinResetPasswordValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.create.ResetPasswordRequestDTO;
import co.davsancamros.ricordati.dto.response.LoginResponseDTO;
import co.davsancamros.ricordati.services.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthControllerImplementation implements IAuthController{

    private final IAuthService authService;


    @Override
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        return ResponseEntity
                .status(200)
                .body(
                        this.authService.login(loginRequestDTO)
                );
    }

    @Override
    public ResponseEntity<Void> validateRegistrationOTP(OneTimePinRegistrationValidationRequestDTO oneTimePinRegistrationValidationRequestDTO) {

        this.authService.validateRegistrationOTP(oneTimePinRegistrationValidationRequestDTO);

        return ResponseEntity
                .status(200)
                .build();
    }

    @Override
    public ResponseEntity<Void> sendOTPtoResetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO) {

        this.authService.resetPassword(resetPasswordRequestDTO);

        return ResponseEntity
                .status(200)
                .build();
    }


    @Override
    public ResponseEntity<Void> validateResetPasswordOTP(OneTimePinResetPasswordValidationRequestDTO oneTimePinResetPasswordValidationRequestDTO) {

        this.authService.validateResetPasswordOTP(oneTimePinResetPasswordValidationRequestDTO);

        return ResponseEntity
                .status(200)
                .build();
    }
}
