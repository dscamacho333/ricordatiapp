package co.davsancamros.ricordati.mapper;

import co.davsancamros.ricordati.dto.request.create.AccountCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.AccountUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.AccountResponseDTO;
import co.davsancamros.ricordati.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    AccountResponseDTO toDTO(Account accountEntity);
    Account toEntity(AccountCreateRequestDTO accountDTO);
    Account toEntitySpecial(AccountResponseDTO accountDTO);
    void updateEntityFromDTO(AccountUpdateRequestDTO accountDTO, @MappingTarget Account accountEntity);

}
