package co.davsancamros.ricordati.controller.interfaces;

import co.davsancamros.ricordati.configurations.CustomUserDetails;
import co.davsancamros.ricordati.dto.request.create.UserCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.UserUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface IUserController {

    @PostMapping
    ResponseEntity<UserResponseDTO> create(
            @RequestBody @Valid UserCreateRequestDTO userDTO
            );

    @GetMapping
    ResponseEntity<UserResponseDTO> findById(
            @AuthenticationPrincipal CustomUserDetails principal
            );

    @PutMapping
    ResponseEntity<UserResponseDTO> updateById(
            @AuthenticationPrincipal CustomUserDetails principal,
            @RequestBody @Valid UserUpdateRequestDTO userDTO
            );

    @DeleteMapping
    ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal CustomUserDetails principal
    );

    @GetMapping("/all")
    ResponseEntity<List<UserResponseDTO>> findAll();

}
