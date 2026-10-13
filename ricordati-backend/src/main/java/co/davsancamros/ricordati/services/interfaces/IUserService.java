package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.dto.request.create.UserCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.UserUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.UserResponseDTO;
import co.davsancamros.ricordati.entity.User;

import java.util.List;

public interface IUserService {

    UserResponseDTO create(UserCreateRequestDTO userDTO);
    UserResponseDTO findById(Long userId);
    UserResponseDTO updateById(Long userId, UserUpdateRequestDTO userDTO);
    void deleteById(Long userId);
    List<UserResponseDTO> findAll();
    User findAndReturnEntityById(Long userId);

}
