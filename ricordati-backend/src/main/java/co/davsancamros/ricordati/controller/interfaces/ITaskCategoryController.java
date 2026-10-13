package co.davsancamros.ricordati.controller.interfaces;

import co.davsancamros.ricordati.configurations.CustomUserDetails;
import co.davsancamros.ricordati.dto.request.create.TaskCategoryCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskCategoryUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskCategoryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/task-category")
public interface ITaskCategoryController {

    @PostMapping
    ResponseEntity<TaskCategoryResponseDTO> create(
            @RequestBody @Valid TaskCategoryCreateRequestDTO taskCategoryDTO,
            @AuthenticationPrincipal CustomUserDetails principal
    );

    @GetMapping("/{taskCategoryId}")
    ResponseEntity<TaskCategoryResponseDTO> findById(
            @PathVariable Long taskCategoryId,
            @AuthenticationPrincipal CustomUserDetails principal
    );

    @PutMapping("/{taskCategoryId}")
    ResponseEntity<TaskCategoryResponseDTO> updateById(
            @PathVariable Long taskCategoryId,
            @RequestBody @Valid TaskCategoryUpdateRequestDTO taskCategoryDTO,
            @AuthenticationPrincipal CustomUserDetails principal);

    @DeleteMapping("/{taskCategoryId}")
    ResponseEntity<Void> deleteById(
            @PathVariable Long taskCategoryId,
            @AuthenticationPrincipal CustomUserDetails principal
    );

    @GetMapping
    ResponseEntity<List<TaskCategoryResponseDTO>> findAllById(
            @AuthenticationPrincipal CustomUserDetails principal
    );

}
