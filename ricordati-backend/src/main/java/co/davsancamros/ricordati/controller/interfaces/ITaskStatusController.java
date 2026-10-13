package co.davsancamros.ricordati.controller.interfaces;

import co.davsancamros.ricordati.dto.response.TaskStatusResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/task-status")
public interface ITaskStatusController {

    @GetMapping
    ResponseEntity<List<TaskStatusResponseDTO>> findAllActive();

}
