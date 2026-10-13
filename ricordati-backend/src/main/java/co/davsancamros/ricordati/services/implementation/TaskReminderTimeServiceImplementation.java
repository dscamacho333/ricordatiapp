package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.response.TaskReminderTimeResponseDTO;
import co.davsancamros.ricordati.entity.TaskReminderTime;
import co.davsancamros.ricordati.mapper.ITaskReminderTimeMapper;
import co.davsancamros.ricordati.repository.ITaskReminderTimeRepository;
import co.davsancamros.ricordati.services.interfaces.ITaskReminderTimeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskReminderTimeServiceImplementation implements ITaskReminderTimeService {

    private final ITaskReminderTimeRepository taskReminderTimeRepository;
    private final ITaskReminderTimeMapper taskReminderTimeMapper;


    @Override
    public TaskReminderTime findAndReturnEntityById(Long taskReminderTimeId) {
        return this.taskReminderTimeRepository
                .findById(taskReminderTimeId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Recordatorio de tarea no existe")
                );
    }

    @Override
    public List<TaskReminderTimeResponseDTO> findAllActive() {
        return this.taskReminderTimeRepository
                .findAllByIsActiveIsTrue()
                .stream()
                .map(
                      this.taskReminderTimeMapper::toDTO
                )
                .toList();
    }
}
