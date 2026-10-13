package co.davsancamros.ricordati.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

}
