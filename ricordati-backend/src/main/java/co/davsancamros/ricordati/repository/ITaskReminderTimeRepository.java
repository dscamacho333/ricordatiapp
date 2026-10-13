package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.TaskReminderTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskReminderTimeRepository extends JpaRepository<TaskReminderTime, Long> {

    List<TaskReminderTime> findAllByIsActiveIsTrue();

}
