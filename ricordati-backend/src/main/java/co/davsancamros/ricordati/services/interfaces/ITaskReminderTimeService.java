package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.response.TaskReminderTimeResponseDTO;
import co.davsancamros.ricordati.entity.TaskReminderTime;

import java.util.List;

public interface ITaskReminderTimeService {

    TaskReminderTime findAndReturnEntityById(Long taskReminderTimeId);
    List<TaskReminderTimeResponseDTO> findAllActive();

}
