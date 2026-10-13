package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.request.create.TaskCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskResponseDTO;

import java.util.List;

public interface ITaskService {

    TaskResponseDTO create(TaskCreateRequestDTO taskDTO, Long userId);
    TaskResponseDTO findById(Long taskId, Long userId);
    TaskResponseDTO updateById(Long taskId, TaskUpdateRequestDTO taskDTO, Long userId);
    void deleteById(Long taskId, Long userId);
    List<TaskResponseDTO> findAll(Long userId);

}
