package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.entity.AccountStatus;

public interface IAccountStatusService {

    AccountStatus findByName(String name);

}
