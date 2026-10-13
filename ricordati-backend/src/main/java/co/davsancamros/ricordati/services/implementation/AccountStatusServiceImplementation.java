package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.entity.AccountStatus;
import co.davsancamros.ricordati.repository.IAccountStatusRepository;
import co.davsancamros.ricordati.services.interfaces.IAccountStatusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountStatusServiceImplementation implements IAccountStatusService{

    private final IAccountStatusRepository accountStatusRepository;


    @Override
    public AccountStatus findByName(String name) {
        return this.accountStatusRepository
                .findByName(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("Estado de cuenta no existe")
                );
    }
}
