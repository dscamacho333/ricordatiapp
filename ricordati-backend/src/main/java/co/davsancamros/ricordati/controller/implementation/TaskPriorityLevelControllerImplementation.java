package co.davsancamros.ricordati.controller.implementation;

import co.davsancamros.ricordati.controller.interfaces.ITaskPriorityLevelController;
import co.davsancamros.ricordati.dto.response.TaskPriorityLevelResponseDTO;
import co.davsancamros.ricordati.services.interfaces.ITaskPriorityLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TaskPriorityLevelControllerImplementation implements ITaskPriorityLevelController {

    private final ITaskPriorityLevelService taskPriorityLevelService;

    @Override
    public ResponseEntity<List<TaskPriorityLevelResponseDTO>> findAllActive() {
        return ResponseEntity
                .status(200)
                .body(
                        this.taskPriorityLevelService.findAllActive()
                );
    }
}
