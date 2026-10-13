package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.request.create.TaskCategoryCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskCategoryUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskCategoryResponseDTO;
import co.davsancamros.ricordati.entity.TaskCategory;

import java.util.List;

public interface ITaskCategoryService {

    TaskCategoryResponseDTO create(TaskCategoryCreateRequestDTO taskCategoryDTO, Long userId);
    TaskCategoryResponseDTO findById(Long taskCategoryId, Long userId);
    TaskCategoryResponseDTO updateById(Long taskCategoryId, TaskCategoryUpdateRequestDTO taskCategoryDTO, Long userId);
    void deleteById(Long taskCategoryId, Long userId);
    List<TaskCategoryResponseDTO> findAll(Long userId);

    TaskCategory findAndReturnEntityById(Long taskCategoryId, Long userId);

}
