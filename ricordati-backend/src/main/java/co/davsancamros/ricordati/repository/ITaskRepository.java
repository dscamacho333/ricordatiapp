package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.Task;
import co.davsancamros.ricordati.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndUserId(Long taskId, Long userId);
    List<Task> findAllByUserId(Long userId);

    Long user(User user);
}
