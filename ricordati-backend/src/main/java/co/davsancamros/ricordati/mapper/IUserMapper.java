package co.davsancamros.ricordati.mapper;

import co.davsancamros.ricordati.dto.request.create.UserCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.UserUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.UserResponseDTO;
import co.davsancamros.ricordati.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",  uses = {IAccountMapper.class})
public interface IUserMapper {

    UserResponseDTO toDTO(User userEntity);
    User toEntity(UserCreateRequestDTO userDTO);
    void updateEntityFromDTO(UserUpdateRequestDTO userDTO, @MappingTarget User userEntity);

}
