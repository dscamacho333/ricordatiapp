package co.davsancamros.ricordati.mapper;

import co.davsancamros.ricordati.dto.request.create.TaskCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskResponseDTO;
import co.davsancamros.ricordati.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ITaskCategoryMapper.class, ITaskStatusMapper.class, ITaskPriorityLevelMapper.class, ITaskReminderTimeMapper.class})
public interface ITaskMapper {

    TaskResponseDTO toDTO(Task taskEntity);
    Task toEntity(TaskCreateRequestDTO taskDTO);
    void updateEntityFromDTO(TaskUpdateRequestDTO taskDTO, @MappingTarget Task taskEntity);

}
