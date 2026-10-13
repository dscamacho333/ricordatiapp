package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.request.create.TaskCategoryCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskCategoryUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskCategoryResponseDTO;
import co.davsancamros.ricordati.entity.TaskCategory;
import co.davsancamros.ricordati.entity.User;
import co.davsancamros.ricordati.exception.NonDeletableResourceException;
import co.davsancamros.ricordati.exception.NonUpdatableResourceException;
import co.davsancamros.ricordati.mapper.ITaskCategoryMapper;
import co.davsancamros.ricordati.repository.ITaskCategoryRepository;
import co.davsancamros.ricordati.services.interfaces.ITaskCategoryService;
import co.davsancamros.ricordati.services.interfaces.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskCategoryServiceImplementation implements ITaskCategoryService {

    private final ITaskCategoryRepository taskCategoryRepository;
    private final ITaskCategoryMapper taskCategoryMapper;
    private final IUserService userService;


    @Override
    @Transactional
    public TaskCategoryResponseDTO create(TaskCategoryCreateRequestDTO taskCategoryDTO, Long userId) {

        TaskCategory taskCategoryEntity = this.taskCategoryMapper
                .toEntity(taskCategoryDTO);

        User user = this.userService
                        .findAndReturnEntityById(userId);

        taskCategoryEntity.setUser(user);
        taskCategoryEntity.setIsActive(Boolean.TRUE);

        taskCategoryEntity = this.taskCategoryRepository
                .save(taskCategoryEntity);

        return this.taskCategoryMapper
                .toDTO(taskCategoryEntity);
    }

    @Override
    public TaskCategoryResponseDTO findById(Long taskCategoryId, Long userId) {

        TaskCategory taskCategoryEntity = this.taskCategoryRepository
                .findByIdAndUserId(taskCategoryId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Categoria de tarea no existe")
                );

        return this.taskCategoryMapper
                .toDTO(taskCategoryEntity);
    }

    @Override
    @Transactional
    public TaskCategoryResponseDTO updateById(Long taskCategoryId, TaskCategoryUpdateRequestDTO taskCategoryDTO, Long userId) {

        TaskCategory taskCategoryEntity = this.taskCategoryRepository
                .findByIdAndUserId(taskCategoryId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Categoria de tarea no existe")
                );

        if (taskCategoryEntity.getIsActive().equals(Boolean.FALSE)){
            throw new NonUpdatableResourceException("Categoria de tarea no existe");
        }

        this.taskCategoryMapper.updateEntityFromDTO(taskCategoryDTO, taskCategoryEntity);

        taskCategoryEntity = this.taskCategoryRepository
                .save(taskCategoryEntity);

        return this.taskCategoryMapper
                .toDTO(taskCategoryEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long taskCategoryId, Long userId) {

        TaskCategory taskCategoryEntity = this.taskCategoryRepository
                .findByIdAndUserId(taskCategoryId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Categoria de tarea no existe")
                );

        if (taskCategoryEntity.getIsActive().equals(Boolean.FALSE)){
            throw new NonDeletableResourceException("Categoria de tarea no existe");
        }

        taskCategoryEntity.setIsActive(Boolean.FALSE);

        this.taskCategoryRepository
                .save(taskCategoryEntity);

    }

    @Override
    public List<TaskCategoryResponseDTO> findAll(Long userId) {
        return this.taskCategoryRepository
                .findAllByUserId(userId)
                .stream()
                .map(this.taskCategoryMapper::toDTO)
                .toList();
    }

    @Override
    public TaskCategory findAndReturnEntityById(Long taskCategoryId, Long userId) {
        return this.taskCategoryRepository
                .findByIdAndUserId(taskCategoryId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Categoria de tarea no existe")
                );
    }

}
