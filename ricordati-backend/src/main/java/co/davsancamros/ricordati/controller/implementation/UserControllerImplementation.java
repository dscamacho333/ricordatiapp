package co.davsancamros.ricordati.controller.implementation;

import co.davsancamros.ricordati.configurations.CustomUserDetails;
import co.davsancamros.ricordati.controller.interfaces.IUserController;
import co.davsancamros.ricordati.dto.request.create.UserCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.UserUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.UserResponseDTO;
import co.davsancamros.ricordati.services.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserControllerImplementation implements IUserController {

    private final IUserService userService;


    @Override
    public ResponseEntity<UserResponseDTO> create(UserCreateRequestDTO userDTO) {
        return ResponseEntity
                .status(201)
                .body(
                        this.userService.create(userDTO)
                );
    }

    @Override
    public ResponseEntity<UserResponseDTO> findById(CustomUserDetails principal) {
        
        return ResponseEntity
                .status(200)
                .body(
                        this.userService.findById(principal.getUserId())
                );
    }

    @Override
    public ResponseEntity<UserResponseDTO> updateById(CustomUserDetails principal, UserUpdateRequestDTO userDTO) {
        return ResponseEntity
                .status(200)
                .body(
                        this.userService.updateById(principal.getUserId(), userDTO)
                );
    }

    @Override
    public ResponseEntity<Void> deleteById(CustomUserDetails principal) {

        this.userService.deleteById(principal.getUserId());

        return ResponseEntity
                .status(204)
                .build();
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity
                .status(200)
                .body(
                        this.userService.findAll()
                );
    }
}
