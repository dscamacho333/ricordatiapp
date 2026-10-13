package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.TaskPriorityLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskPriorityLevelRepository extends JpaRepository<TaskPriorityLevel, Long> {

    List<TaskPriorityLevel> findAllByIsActiveIsTrue();

}
