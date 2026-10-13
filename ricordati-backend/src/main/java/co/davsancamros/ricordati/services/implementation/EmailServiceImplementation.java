package co.davsancamros.ricordati.services.implementation;

import co.davsancamros.ricordati.entity.OneTimePin;
import co.davsancamros.ricordati.entity.User;
import co.davsancamros.ricordati.services.interfaces.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class EmailServiceImplementation implements IEmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ricordatiapp@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);

    }

    @Override
    public void sendWelcomeEmail(User user, OneTimePin oneTimePin) {

        String fullName = user.getFirstName() + " " + user.getLastName();
        String otp = oneTimePin.getOtp();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
                "dd/MM/yy HH:mm"
        );
        String deadLine = oneTimePin.getExpiresAt().format(dateTimeFormatter);
        String to = user.getAccount().getEmail();
        String subject = "Bienvenida de Ricordati";
        String message =
                """
                Hola, %s!
                
                Desde Ricordati App queremos darte la bienvenida a nuestra plataforma. ¿Listo para recordar todo lo que debes hacer?
                
                Para poder activar tu cuenta con nosotros, ingresa el siguiente OTP: %s, esta acción debe ser llevada a cabo antes de %s
                
                """.formatted(
                        fullName,
                        otp,
                        deadLine
                );

        this.sendEmail(to, subject, message);

    }

    @Override
    public void sendActivationConfirmationEmail(User user) {

        String fullName = user.getFirstName() + " " + user.getLastName();
        String to = user.getAccount().getEmail();
        String subject = "Confirmación de activación de cuenta";
        String message =
                """
                Hola, %s!
                
                Ya hemos recibido tu confirmación de la activación de tu cuenta con nosotros. Empieza a recordar desde hoy.
                
                """.formatted(
                        fullName
                );

        this.sendEmail(to, subject, message);
    }

    @Override
    public void sendResetPasswordEmail(User user, OneTimePin oneTimePin) {

        String fullName = user.getFirstName() + " " + user.getLastName();
        String otp = oneTimePin.getOtp();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
                "dd/MM/yy HH:mm"
        );
        String deadLine = oneTimePin.getExpiresAt().format(dateTimeFormatter);
        String to = user.getAccount().getEmail();
        String subject = "OTP para actualización de contraseña";
        String message =
                """
                Hola, %s!
                
                Desde el equipo de Ricordati App entendemos que se te pueden olvidar las cosas de vez en cuando. Usa este OTP: %s para actualizar tu contraseña, tienes hasta %s para realizar este cambio. 
                
                """.formatted(
                        fullName,
                        otp,
                        deadLine
                );

        this.sendEmail(to, subject, message);

    }
}
