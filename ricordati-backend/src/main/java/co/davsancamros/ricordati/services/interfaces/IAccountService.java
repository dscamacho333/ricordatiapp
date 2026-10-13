package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.request.create.AccountCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.create.OneTimePinResetPasswordValidationRequestDTO;
import co.davsancamros.ricordati.dto.request.update.AccountUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.AccountResponseDTO;
import co.davsancamros.ricordati.entity.Account;

import java.util.List;

public interface IAccountService {

    Account create(AccountCreateRequestDTO accountDTO);
    AccountResponseDTO findById(Long accountId);
    Account updateById(Long accountId, AccountUpdateRequestDTO accountDTO);
    void deleteById(Long accountId);
    List<AccountResponseDTO> findAll();
    Account findByEmail(String email);
    void activateAccount(String email);
    void resetPassword(OneTimePinResetPasswordValidationRequestDTO oneTimePinResetPasswordValidationRequestDTO);

}
