package co.davsancamros.ricordati.controller.implementation;

import co.davsancamros.ricordati.configurations.CustomUserDetails;
import co.davsancamros.ricordati.controller.interfaces.ITaskCategoryController;
import co.davsancamros.ricordati.dto.request.create.TaskCategoryCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskCategoryUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskCategoryResponseDTO;
import co.davsancamros.ricordati.services.interfaces.ITaskCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TaskCategoryControllerImplementation implements ITaskCategoryController {

    private final ITaskCategoryService taskCategoryService;


    @Override
    public ResponseEntity<TaskCategoryResponseDTO> create(TaskCategoryCreateRequestDTO taskCategoryDTO, CustomUserDetails principal) {
        return ResponseEntity
                .status(201)
                .body(
                        this.taskCategoryService.create(taskCategoryDTO, principal.getUserId())
                );
    }

    @Override
    public ResponseEntity<TaskCategoryResponseDTO> findById(Long taskCategoryId, CustomUserDetails principal) {
        return ResponseEntity
                .status(200)
                .body(
                        this.taskCategoryService.findById(taskCategoryId, principal.getUserId())
                );
    }



    @Override
    public ResponseEntity<TaskCategoryResponseDTO> updateById(Long taskCategoryId, TaskCategoryUpdateRequestDTO taskCategoryDTO, CustomUserDetails principal) {
        return ResponseEntity
                .status(200)
                .body(
                      this.taskCategoryService.updateById(taskCategoryId, taskCategoryDTO, principal.getUserId())
                );
    }

    @Override
    public ResponseEntity<Void> deleteById(Long taskCategoryId, CustomUserDetails principal) {

        this.taskCategoryService.deleteById(taskCategoryId, principal.getUserId());

        return ResponseEntity
                .status(204)
                .build();
    }

    @Override
    public ResponseEntity<List<TaskCategoryResponseDTO>> findAllById(CustomUserDetails principal) {
        return ResponseEntity
                .status(200)
                .body(
                        this.taskCategoryService.findAll(principal.getUserId())
                );
    }
}
