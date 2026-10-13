package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.response.TaskPriorityLevelResponseDTO;
import co.davsancamros.ricordati.entity.TaskPriorityLevel;

import java.util.List;

public interface ITaskPriorityLevelService {

    TaskPriorityLevel findAndReturnEntityById(Long taskPriorityLevelId);
    List<TaskPriorityLevelResponseDTO> findAllActive();

}
