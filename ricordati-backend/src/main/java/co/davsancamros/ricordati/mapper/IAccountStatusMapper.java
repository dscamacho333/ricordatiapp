package co.davsancamros.ricordati.mapper;

import co.davsancamros.ricordati.dto.response.AccountStatusResponseDTO;
import co.davsancamros.ricordati.entity.AccountStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountStatusMapper {

    AccountStatusResponseDTO toDTO(AccountStatus accountStatusEntity);
    AccountStatus toEntity(AccountStatusResponseDTO accountStatusDTO);

}
