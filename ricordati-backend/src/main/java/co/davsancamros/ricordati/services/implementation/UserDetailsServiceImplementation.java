package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.configurations.CustomUserDetails;
import co.davsancamros.ricordati.entity.Account;
import co.davsancamros.ricordati.repository.IAccountRepository;
import co.davsancamros.ricordati.services.interfaces.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final IAccountService accountService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account accountEntity = this.accountService
                .findByEmail(username);

        return new CustomUserDetails(
                accountEntity.getUser().getId(),
                accountEntity.getEmail(),
                accountEntity.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + accountEntity.getRole().name()
                        )
                )
        );


    }
}
