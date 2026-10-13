package co.davsancamros.ricordati.controller.implementation;

import co.davsancamros.ricordati.configurations.CustomUserDetails;
import co.davsancamros.ricordati.controller.interfaces.ITaskController;
import co.davsancamros.ricordati.dto.request.create.TaskCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskResponseDTO;
import co.davsancamros.ricordati.services.interfaces.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TaskControllerImplementation implements ITaskController {

    private final ITaskService taskService;


    @Override
    public ResponseEntity<TaskResponseDTO> create(TaskCreateRequestDTO taskDTO, CustomUserDetails principal) {
        return ResponseEntity
                .status(201)
                .body(
                        this.taskService.create(taskDTO, principal.getUserId())
                );
    }

    @Override
    public ResponseEntity<TaskResponseDTO> findById(Long taskId, CustomUserDetails principal) {
        return ResponseEntity
                .status(200)
                .body(
                        this.taskService.findById(taskId, principal.getUserId())
                );
    }

    @Override
    public ResponseEntity<TaskResponseDTO> updateById(Long taskId, TaskUpdateRequestDTO taskDTO, CustomUserDetails principal) {
        return ResponseEntity
                .status(200)
                .body(
                        this.taskService.updateById(taskId, taskDTO, principal.getUserId())
                );
    }

    @Override
    public ResponseEntity<Void> deleteById(Long taskId, CustomUserDetails principal) {

        this.taskService.deleteById(taskId, principal.getUserId());

        return ResponseEntity
                .status(204)
                .build();
    }

    @Override
    public ResponseEntity<List<TaskResponseDTO>> findAll(CustomUserDetails principal) {
        return ResponseEntity
                .status(200)
                .body(
                        this.taskService.findAll(principal.getUserId())
                );
    }
}
