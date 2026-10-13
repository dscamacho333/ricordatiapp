package co.davsancamros.ricordati.controller.interfaces;

import co.davsancamros.ricordati.dto.response.TaskPriorityLevelResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/task-priority-level")
public interface ITaskPriorityLevelController {

    @GetMapping
    ResponseEntity<List<TaskPriorityLevelResponseDTO>> findAllActive();

}
