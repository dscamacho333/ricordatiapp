package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.response.TaskPriorityLevelResponseDTO;
import co.davsancamros.ricordati.entity.TaskPriorityLevel;
import co.davsancamros.ricordati.mapper.ITaskPriorityLevelMapper;
import co.davsancamros.ricordati.repository.ITaskPriorityLevelRepository;
import co.davsancamros.ricordati.services.interfaces.ITaskPriorityLevelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskPriorityLevelServiceImplementation implements ITaskPriorityLevelService {

    private final ITaskPriorityLevelRepository taskPriorityLevelRepository;
    private final ITaskPriorityLevelMapper taskPriorityLevelMapper;


    @Override
    public TaskPriorityLevel findAndReturnEntityById(Long taskPriorityLevelId) {
        return this.taskPriorityLevelRepository
                .findById(taskPriorityLevelId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Prioridad de tarea no existe")
                );
    }

    @Override
    public List<TaskPriorityLevelResponseDTO> findAllActive() {
        return this.taskPriorityLevelRepository
                .findAllByIsActiveIsTrue()
                .stream()
                .map(
                        this.taskPriorityLevelMapper::toDTO
                )
                .toList();
    }
}
