package co.davsancamros.ricordati.services.interfaces;

import co.davsancamros.ricordati.entity.OneTimePin;
import co.davsancamros.ricordati.entity.User;

public interface IEmailService {

    void sendEmail(String to, String subject, String body);
    void sendWelcomeEmail(User user, OneTimePin oneTimePin);
    void sendActivationConfirmationEmail(User user);
    void sendResetPasswordEmail(User user, OneTimePin oneTimePin);

}
