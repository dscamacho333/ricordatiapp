package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.TaskCategory;
import co.davsancamros.ricordati.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

    Optional<TaskCategory> findByIdAndUserId(Long taskCategoryId, Long userId);
    List<TaskCategory> findAllByUserId(Long userId);
}
