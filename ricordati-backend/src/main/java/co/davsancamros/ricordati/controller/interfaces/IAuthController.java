package co.davsancamros.ricordati.controller.interfaces;

import co.davsancamros.ricordati.dto.request.create.LoginRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinRegistrationValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinResetPasswordValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.create.ResetPasswordRequestDTO;
import co.davsancamros.ricordati.dto.response.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface IAuthController {


    @PostMapping
    ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO loginRequestDTO
            );

    @PostMapping("/registration-otp")
    ResponseEntity<Void> validateRegistrationOTP(
            @RequestBody @Valid OneTimePinRegistrationValidationRequestDTO oneTimePinRegistrationValidationRequestDTO
    );

    @PostMapping("/reset-password")
    ResponseEntity<Void> sendOTPtoResetPassword(
            @RequestBody @Valid ResetPasswordRequestDTO resetPasswordRequestDTO
            );

    @PostMapping("/reset-password-otp")
    ResponseEntity<Void> validateResetPasswordOTP(
            @RequestBody @Valid OneTimePinResetPasswordValidationRequestDTO oneTimePinResetPasswordValidationRequestDTO
            );



}
