package co.davsancamros.ricordati.controller.interfaces;

import co.davsancamros.ricordati.configurations.CustomUserDetails;
import co.davsancamros.ricordati.dto.request.create.TaskCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
public interface ITaskController {

    @PostMapping
    ResponseEntity<TaskResponseDTO> create(
            @RequestBody @Valid TaskCreateRequestDTO taskDTO,
            @AuthenticationPrincipal CustomUserDetails principal
    );

    @GetMapping("/{taskId}")
    ResponseEntity<TaskResponseDTO> findById(
            @PathVariable Long taskId,
            @AuthenticationPrincipal CustomUserDetails principal
    );

    @PutMapping("/{taskId}")
    ResponseEntity<TaskResponseDTO> updateById(
            @PathVariable Long taskId,
            @RequestBody @Valid TaskUpdateRequestDTO taskDTO,
            @AuthenticationPrincipal CustomUserDetails principal
    );

    @DeleteMapping("/{taskId}")
    ResponseEntity<Void> deleteById(
            @PathVariable Long taskId,
            @AuthenticationPrincipal CustomUserDetails principal
    );

    @GetMapping
    ResponseEntity<List<TaskResponseDTO>> findAll(
            @AuthenticationPrincipal CustomUserDetails principal
    );

}
