package co.davsancamros.ricordati.mapper;

import co.davsancamros.ricordati.dto.response.TaskPriorityLevelResponseDTO;
import co.davsancamros.ricordati.entity.TaskPriorityLevel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITaskPriorityLevelMapper {

    TaskPriorityLevelResponseDTO toDTO(TaskPriorityLevel taskPriorityLevelEntity);

}
