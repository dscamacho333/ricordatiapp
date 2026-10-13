package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskStatusRepository extends JpaRepository<TaskStatus, Long> {

    List<TaskStatus> findAllByIsActiveIsTrue();

}
