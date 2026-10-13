package co.davsancamros.ricordati.controller.interfaces;

import co.davsancamros.ricordati.dto.response.TaskReminderTimeResponseDTO;
import co.davsancamros.ricordati.entity.TaskReminderTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/task-reminder-time")
public interface ITaskReminderTimeController {

    @GetMapping
    ResponseEntity<List<TaskReminderTimeResponseDTO>> findAllActive();

}
