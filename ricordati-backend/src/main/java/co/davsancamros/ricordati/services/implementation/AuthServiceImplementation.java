package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.request.create.LoginRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinRegistrationValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinResetPasswordValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.create.ResetPasswordRequestDTO;
import co.davsancamros.ricordati.dto.response.LoginResponseDTO;
import co.davsancamros.ricordati.entity.Account;
import co.davsancamros.ricordati.entity.OneTimePin;
import co.davsancamros.ricordati.entity.User;
import co.davsancamros.ricordati.enums.OneTimePinType;
import co.davsancamros.ricordati.exception.AuthException;
import co.davsancamros.ricordati.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImplementation implements IAuthService {


    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final IOneTimePinService oneTimePinService;
    private final IAccountService accountService;
    private final IEmailService emailService;


    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        Account accountEntity = this.accountService
                        .findByEmail(request.getEmail());

        if (accountEntity.getAccountStatus().getName().equals("Inactiva")){

            throw new AuthException("Cuenta no existente o Inactiva");

        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());

        String token = this.jwtService
                .generateToken(userDetails);

        return new LoginResponseDTO(
                "Bearer",
                token
        );

    }

    @Override
    @Transactional
    public void validateRegistrationOTP(OneTimePinRegistrationValidationRequestDTO oneTimePinRegistrationValidationRequestDTO) {

        this.oneTimePinService
                .validateOTP(oneTimePinRegistrationValidationRequestDTO, OneTimePinType.REGISTRATION);

        this.accountService
                .activateAccount(oneTimePinRegistrationValidationRequestDTO.getEmail());

    }

    @Override
    public void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO) {

        Account accountEntity = this.accountService
                .findByEmail(resetPasswordRequestDTO.getEmail());

        User userEntity = accountEntity.getUser();

        OneTimePin oneTimePinEntity = this.oneTimePinService
            .create(userEntity.getId(), OneTimePinType.PASSWORD_RESET);

        this.emailService
                .sendResetPasswordEmail(userEntity, oneTimePinEntity);

    }


    @Override
    @Transactional
    public void validateResetPasswordOTP(OneTimePinResetPasswordValidationRequestDTO oneTimePinResetPasswordValidationRequestDTO) {



        this.oneTimePinService
                .validateOTP(oneTimePinResetPasswordValidationRequestDTO, OneTimePinType.PASSWORD_RESET);

        this.accountService
                .resetPassword(oneTimePinResetPasswordValidationRequestDTO);

    }


}
