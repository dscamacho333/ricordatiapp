package co.davsancamros.ricordati.controller.interfaces;

import co.davsancamros.ricordati.dto.request.create.AccountCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.AccountUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.AccountResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/account")
public interface IAccountController {


    @GetMapping("/{accountId}")
    ResponseEntity<AccountResponseDTO> findById(
            @PathVariable Long accountId
    );

    @DeleteMapping("/{accountId}")
    ResponseEntity<Void> deleteById(
            @PathVariable Long accountId
    );

    @GetMapping
    ResponseEntity<List<AccountResponseDTO>> findAll();

}
