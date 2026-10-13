package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.dto.request.create.UserCreateRequestDTO;
import co.davsancamros.ricordati.dto.request.update.UserUpdateRequestDTO;
import co.davsancamros.ricordati.dto.response.UserResponseDTO;
import co.davsancamros.ricordati.entity.Account;
import co.davsancamros.ricordati.entity.OneTimePin;
import co.davsancamros.ricordati.entity.User;
import co.davsancamros.ricordati.enums.OneTimePinType;
import co.davsancamros.ricordati.exception.NonCreatableResourceException;
import co.davsancamros.ricordati.exception.NonDeletableResourceException;
import co.davsancamros.ricordati.mapper.IUserMapper;
import co.davsancamros.ricordati.repository.IUserRepository;
import co.davsancamros.ricordati.services.interfaces.IAccountService;
import co.davsancamros.ricordati.services.interfaces.IEmailService;
import co.davsancamros.ricordati.services.interfaces.IOneTimePinService;
import co.davsancamros.ricordati.services.interfaces.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final IAccountService accountService;
    private final IOneTimePinService oneTimePinService;
    private final IEmailService emailService;

    @Override
    @Transactional
    public UserResponseDTO create(UserCreateRequestDTO userDTO) {

        User userEntity = this.userMapper
                .toEntity(userDTO);

        if (this.userRepository.existsByGovernmentId(userEntity.getGovernmentId())){
            throw new NonCreatableResourceException("Cédula con la que se intenta registrar ya está en uso");
        }

        if (Period.between(userEntity.getDateOfBirth(), LocalDate.now()).getYears() < 18){
            throw new NonCreatableResourceException("Usuario no puede ser registrado si no tiene al menos 18 años");
        }

        Account accountEntity = this.accountService
                        .create(userDTO.getAccount());

        userEntity.setAccount(accountEntity);


        userEntity = this.userRepository
                .save(userEntity);

        OneTimePin oneTimePin = this.oneTimePinService
                .create(userEntity.getId(), OneTimePinType.REGISTRATION);

        this.emailService.sendWelcomeEmail(userEntity, oneTimePin);

        return this.userMapper
                .toDTO(userEntity);
    }

    @Override
    public UserResponseDTO findById(Long userId) {

        User userEntity = this.userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Usuario no existe")
                );

        return this.userMapper
                .toDTO(userEntity);
    }

    @Override
    @Transactional
    public UserResponseDTO updateById(Long userId, UserUpdateRequestDTO userDTO) {

        User userEntity = this.userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Usuario no existe")
                );

        Account accountEntity = this.accountService
                .updateById(userEntity.getAccount().getId(), userDTO.getAccount());


        this.userMapper.updateEntityFromDTO(userDTO, userEntity);

        userEntity.setAccount(accountEntity);

        userEntity = this.userRepository
                .save(userEntity);

        return this.userMapper
                .toDTO(
                        userEntity
                );
    }

    @Override
    @Transactional
    public void deleteById(Long userId) {

        User userEntity = this.userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Usuario no existe")
                );

        if (userEntity.getAccount().getAccountStatus().getName().equals("Inactiva")){
            throw new NonDeletableResourceException("Usuario no existe");
        }

        this.accountService
                .deleteById(userEntity.getAccount().getId());

    }

    @Override
    public List<UserResponseDTO> findAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(this.userMapper::toDTO)
                .toList();
    }

    @Override
    public User findAndReturnEntityById(Long userId) {
        return this.userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Usuario no existe")
                );
    }

}
