package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.request.create.AccountCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinResetPasswordValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.update.AccountUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.AccountResponseDTO;
import co.davsancamros.ricordati.entity.Account;
import co.davsancamros.ricordati.entity.AccountStatus;
import co.davsancamros.ricordati.enums.Role;
import co.davsancamros.ricordati.exception.NonCreatableResourceException;
import co.davsancamros.ricordati.exception.NonUpdatableResourceException;
import co.davsancamros.ricordati.mapper.IAccountMapper;
import co.davsancamros.ricordati.repository.IAccountRepository;
import co.davsancamros.ricordati.services.interfaces.IAccountService;
import co.davsancamros.ricordati.services.interfaces.IAccountStatusService;
import co.davsancamros.ricordati.services.interfaces.IEmailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImplementation implements IAccountService {

    private final IAccountRepository accountRepository;
    private final IAccountMapper accountMapper;
    private final IAccountStatusService accountStatusService;
    private final PasswordEncoder passwordEncoder;
    private final IEmailService emailService;


    @Override
    @Transactional
    public Account create(AccountCreateRequestDTO accountDTO) {

        Account accountEntity = this.accountMapper.toEntity(accountDTO);

        if (accountRepository.existsByEmail(accountEntity.getEmail())){
            throw new NonCreatableResourceException("Correo ya está en uso");
        }

        accountEntity.setPassword(
                passwordEncoder
                        .encode(
                                accountEntity
                                        .getPassword()
                        )
        );

        AccountStatus accountStatusEntity = this.accountStatusService
                        .findByName("Inactiva");

        accountEntity.setAccountStatus(accountStatusEntity);

        accountEntity.setCreatedAt(LocalDate.now());

        accountEntity.setRole(Role.REGULAR_USER);

        this.accountRepository.save(accountEntity);

        return this.accountRepository
                .save(accountEntity);
    }

    @Override
    public AccountResponseDTO findById(Long accountId) {

        Account accountEntity = this.accountRepository
                .findById(accountId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Cuenta no existe")
                );

        return this.accountMapper
                .toDTO(accountEntity);
    }


    @Override
    @Transactional
    public Account updateById(Long accountId, AccountUpdateRequestDTO accountDTO) {

        Account accountEntity = this.accountRepository
                .findById(accountId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Cuenta no existe")
                );

        if (accountEntity.getAccountStatus().getName().equals("Inactiva")){

            throw new NonUpdatableResourceException("Cuenta no existe");

        }

        this.accountMapper.updateEntityFromDTO(accountDTO, accountEntity);

        return this.accountRepository
                .save(accountEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long accountId) {

        Account accountEntity = this.accountRepository
                .findById(accountId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Cuenta no existe")
                );

        AccountStatus accountStatusEntity = this.accountStatusService
                        .findByName("Inactiva");

        accountEntity.setAccountStatus(accountStatusEntity);

        this.accountRepository.save(accountEntity);

    }

    @Override
    public List<AccountResponseDTO> findAll() {
        return this.accountRepository
                .findAll()
                .stream()
                .map(
                        this.accountMapper::toDTO
                )
                .toList();
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Cuenta no encontrada")
                );
    }

    @Override
    @Transactional
    public void activateAccount(String email) {

        Account accountEntity = this.findByEmail(email);

        if (accountEntity.getAccountStatus().getName().equals("Activa")){

            throw new NonUpdatableResourceException("Cuenta ya está activa");

        }

        AccountStatus accountStatusEntity = this.accountStatusService
                .findByName("Activa");

        accountEntity.setAccountStatus(
                accountStatusEntity
        );

        this.accountRepository
                .save(accountEntity);

        this.emailService
                .sendActivationConfirmationEmail(accountEntity.getUser());

    }


    @Override
    public void resetPassword(OneTimePinResetPasswordValidationRequestDTO oneTimePinResetPasswordValidationRequestDTO) {

        Account accountEntity = this.findByEmail(oneTimePinResetPasswordValidationRequestDTO.getEmail());

        validateNewPasswordConfirmation(
                oneTimePinResetPasswordValidationRequestDTO.getNewPassword(),
                oneTimePinResetPasswordValidationRequestDTO.getNewPasswordConfirmation()
        );

        accountEntity.setPassword(
                passwordEncoder
                        .encode(
                                oneTimePinResetPasswordValidationRequestDTO.getNewPassword()
                        )
        );

        this.accountRepository
                .save(accountEntity);

    }

    private void validateNewPasswordConfirmation(String newPassword, String newPasswordConfirmation){

        if (!newPassword.equals(newPasswordConfirmation)){
            throw new NonUpdatableResourceException("La nueva contraseña y su confirmación no coinciden");
        }

    }

}
