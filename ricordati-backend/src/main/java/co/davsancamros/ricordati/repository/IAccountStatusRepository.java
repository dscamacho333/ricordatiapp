package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountStatusRepository extends JpaRepository<AccountStatus, Long> {

    Optional<AccountStatus> findByName(String name);

}
