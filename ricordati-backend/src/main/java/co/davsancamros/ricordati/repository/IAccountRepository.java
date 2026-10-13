package co.davsancamros.ricordati.repository;

import co.davsancamros.ricordati.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    boolean existsByEmail(String email);

}
