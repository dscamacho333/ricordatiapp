package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.response.TaskStatusResponseDTO;
import co.davsancamros.ricordati.entity.TaskStatus;
import co.davsancamros.ricordati.mapper.ITaskStatusMapper;
import co.davsancamros.ricordati.repository.ITaskStatusRepository;
import co.davsancamros.ricordati.services.interfaces.ITaskStatusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskStatusServiceImplementation implements ITaskStatusService {

    private final ITaskStatusRepository taskStatusRepository;
    private final ITaskStatusMapper taskStatusMapper;

    @Override
    public TaskStatus findAndReturnEntityById(Long taskStatusId) {
        return this.taskStatusRepository
                .findById(taskStatusId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Estado de tarea no existe")
                );
    }

    @Override
    public List<TaskStatusResponseDTO> findAllActive() {
        return this.taskStatusRepository
                .findAllByIsActiveIsTrue()
                .stream()
                .map(
                        this.taskStatusMapper::toDTO
                )
                .toList();
    }
}
