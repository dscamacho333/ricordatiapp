package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.request.create.TaskCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.TaskUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.TaskResponseDTO;
import co.davsancamros.ricordati.entity.*;
import co.davsancamros.ricordati.exception.NonDeletableResourceException;
import co.davsancamros.ricordati.exception.NonUpdatableResourceException;
import co.davsancamros.ricordati.mapper.ITaskMapper;
import co.davsancamros.ricordati.repository.ITaskRepository;
import co.davsancamros.ricordati.services.interfaces.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImplementation implements ITaskService {

    private final ITaskRepository taskRepository;
    private final ITaskMapper taskMapper;
    private final ITaskCategoryService taskCategoryService;
    private final ITaskStatusService taskStatusService;
    private final ITaskPriorityLevelService taskPriorityLevelService;
    private final ITaskReminderTimeService taskReminderTimeService;
    private final IUserService userService;


    @Override
    @Transactional
    public TaskResponseDTO create(TaskCreateRequestDTO taskDTO, Long userId) {

        TaskCategory taskCategoryEntity = this.taskCategoryService
                .findAndReturnEntityById(taskDTO.getTaskCategoryId(), userId);

        TaskStatus taskStatusEntity = this.taskStatusService
                .findAndReturnEntityById(taskDTO.getTaskStatusId());

        TaskPriorityLevel taskPriorityLevelEntity = this.taskPriorityLevelService
                .findAndReturnEntityById(taskDTO.getTaskPriorityLevelId());

        TaskReminderTime taskReminderTimeEntity = this.taskReminderTimeService
                .findAndReturnEntityById(taskDTO.getTaskReminderTimeId());

        User userEntity = this.userService
                .findAndReturnEntityById(userId);

        Task taskEntity = this.taskMapper
                .toEntity(taskDTO);

        taskEntity.setCreationDate(LocalDate.now());
        taskEntity.setTaskCategory(taskCategoryEntity);
        taskEntity.setTaskStatus(taskStatusEntity);
        taskEntity.setTaskPriorityLevel(taskPriorityLevelEntity);
        taskEntity.setTaskReminderTime(taskReminderTimeEntity);
        taskEntity.setUser(userEntity);
        taskEntity.setIsActive(Boolean.TRUE);

        taskEntity = this.taskRepository
                .saveAndFlush(taskEntity);

        return this.taskMapper
                .toDTO(taskEntity);
    }

    @Override
    public TaskResponseDTO findById(Long taskId, Long userId) {

        Task taskEntity = this.taskRepository
                .findByIdAndUserId(taskId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Tarea no existe")
                );

        return this.taskMapper
                .toDTO(taskEntity);
    }

    @Override
    @Transactional
    public TaskResponseDTO updateById(Long taskId, TaskUpdateRequestDTO taskDTO, Long userId) {

        TaskCategory taskCategoryEntity = this.taskCategoryService
                .findAndReturnEntityById(taskDTO.getTaskCategoryId(), userId);

        TaskStatus taskStatusEntity = this.taskStatusService
                .findAndReturnEntityById(taskDTO.getTaskStatusId());

        TaskPriorityLevel taskPriorityLevelEntity = this.taskPriorityLevelService
                .findAndReturnEntityById(taskDTO.getTaskPriorityLevelId());

        TaskReminderTime taskReminderTimeEntity = this.taskReminderTimeService
                .findAndReturnEntityById(taskDTO.getTaskReminderTimeId());

        Task taskEntity = this.taskRepository
                .findByIdAndUserId(taskId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Tarea no existe")
                );

        if (taskEntity.getIsActive().equals(Boolean.FALSE)){
            throw new NonUpdatableResourceException("Tarea no existe");
        }

        taskEntity.setTaskCategory(taskCategoryEntity);
        taskEntity.setTaskStatus(taskStatusEntity);
        taskEntity.setTaskPriorityLevel(taskPriorityLevelEntity);
        taskEntity.setTaskReminderTime(taskReminderTimeEntity);

        this.taskMapper.updateEntityFromDTO(taskDTO, taskEntity);

        this.taskRepository.save(taskEntity);

        return this.taskMapper
                .toDTO(taskEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long taskId, Long userId) {

        Task taskEntity = this.taskRepository
                .findByIdAndUserId(taskId, userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Tarea no existe")
                );

        if (taskEntity.getIsActive().equals(Boolean.FALSE)){
            throw new NonDeletableResourceException("Tarea no existe");
        }

        taskEntity.setIsActive(Boolean.FALSE);

        this.taskRepository.save(taskEntity);

    }

    @Override
    public List<TaskResponseDTO> findAll(Long userId) {
        return this.taskRepository
                .findAllByUserId(userId)
                .stream()
                .map(
                        this.taskMapper::toDTO
                )
                .toList();
    }
}
