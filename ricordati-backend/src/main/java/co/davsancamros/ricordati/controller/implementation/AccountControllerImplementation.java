package co.davsancamros.ricordati.controller.implementation;

import co.davsancamros.ricordati.controller.interfaces.IAccountController;
import co.davsancamros.ricordati.dto.request.create.AccountCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.AccountUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.AccountResponseDTO;
import co.davsancamros.ricordati.services.interfaces.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
// @RestController
public class AccountControllerImplementation implements IAccountController {

    private final IAccountService accountService;


    @Override
    public ResponseEntity<AccountResponseDTO> findById(Long accountId) {
        return ResponseEntity
                .status(200)
                .body(
                        this.accountService.findById(accountId)
                );
    }


    @Override
    public ResponseEntity<Void> deleteById(Long accountId) {

        this.accountService.deleteById(accountId);

        return ResponseEntity
                .status(204)
                .build();
    }

    @Override
    public ResponseEntity<List<AccountResponseDTO>> findAll() {
        return ResponseEntity
                .status(200)
                .body(
                        this.accountService.findAll()
                );
    }
}
