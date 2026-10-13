package co.davsancamros.ricordati.mapper;

import co.davsancamros.ricordati.dto.response.TaskStatusResponseDTO;
import co.davsancamros.ricordati.entity.TaskStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITaskStatusMapper {

    TaskStatusResponseDTO toDTO(TaskStatus taskStatusEntity);

}
