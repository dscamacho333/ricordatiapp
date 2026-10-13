package co.davsancamros.ricordati.mapper;

import co.davsancamros.ricordati.dto.response.TaskReminderTimeResponseDTO;
import co.davsancamros.ricordati.entity.TaskReminderTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITaskReminderTimeMapper {

    TaskReminderTimeResponseDTO toDTO(TaskReminderTime taskReminderTimeEntity);

}
