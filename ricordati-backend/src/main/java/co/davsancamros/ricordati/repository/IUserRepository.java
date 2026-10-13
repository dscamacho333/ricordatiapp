package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    boolean existsByGovernmentId(String governmentId);

}
