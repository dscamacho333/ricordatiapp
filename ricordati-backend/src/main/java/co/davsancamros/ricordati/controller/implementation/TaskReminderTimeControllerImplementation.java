package co.davsancamros.ricordati.controller.implementation;

import co.davsancamros.ricordati.controller.interfaces.ITaskReminderTimeController;
import co.davsancamros.ricordati.dto.response.TaskReminderTimeResponseDTO;
import co.davsancamros.ricordati.services.interfaces.ITaskReminderTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TaskReminderTimeControllerImplementation implements ITaskReminderTimeController {

    private final ITaskReminderTimeService taskReminderTimeService;


    @Override
    public ResponseEntity<List<TaskReminderTimeResponseDTO>> findAllActive() {
        return ResponseEntity
                .status(200)
                .body(
                        this.taskReminderTimeService.findAllActive()
                );
    }
}
