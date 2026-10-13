package co.davsancamros.ricordati.controller.implementation;

import co.davsancamros.ricordati.controller.interfaces.ITaskStatusController;
import co.davsancamros.ricordati.dto.response.TaskStatusResponseDTO;
import co.davsancamros.ricordati.services.interfaces.ITaskStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TaskStatusControllerImplementation implements ITaskStatusController {

    private final ITaskStatusService taskStatusService;

    @Override
    public ResponseEntity<List<TaskStatusResponseDTO>> findAllActive() {
        return ResponseEntity
                .status(200)
                .body(
                        this.taskStatusService.findAllActive()
                );
    }
}
