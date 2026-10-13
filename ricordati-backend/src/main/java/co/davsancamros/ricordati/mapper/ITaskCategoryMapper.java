package co.davsancamros.ricordati.mapper;

import co.davsancamros.ricordati.dto.request.create.TaskCategoryCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskCategoryUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskCategoryResponseDTO;
import co.davsancamros.ricordati.entity.TaskCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ITaskCategoryMapper {

    TaskCategoryResponseDTO toDTO(TaskCategory taskCategoryEntity);
    TaskCategory toEntity(TaskCategoryCreateRequestDTO taskCategoryDTO);
    void updateEntityFromDTO(TaskCategoryUpdateRequestDTO taskCategoryDTO, @MappingTarget TaskCategory taskCategoryEntity);

}
