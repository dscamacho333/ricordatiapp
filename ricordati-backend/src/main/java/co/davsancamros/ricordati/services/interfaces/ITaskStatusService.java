package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.response.TaskStatusResponseDTO;
import co.davsancamros.ricordati.entity.TaskStatus;

import java.util.List;

public interface ITaskStatusService {

    TaskStatus findAndReturnEntityById(Long taskStatusId);
    List<TaskStatusResponseDTO> findAllActive();

}
