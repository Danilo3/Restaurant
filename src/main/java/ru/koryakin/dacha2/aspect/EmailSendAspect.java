package ru.koryakin.dacha2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.koryakin.dacha2.annotations.EmailSend;
import ru.koryakin.dacha2.domain.TextView;
import ru.koryakin.dacha2.services.EmailService;

@Aspect
@Component
public class EmailSendAspect {

    private final EmailService emailService;

    @Autowired
    public EmailSendAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @After(value = "@annotation(ru.koryakin.dacha2.annotations.EmailSend)")
    public void sendEmail(JoinPoint joinPoint) {
        TextView view = (TextView) joinPoint.getArgs()[0];
        try {
            EmailSend annotation = joinPoint.getTarget().getClass().getMethod("save", joinPoint.getArgs()[0].getClass()).getAnnotation(EmailSend.class);
            emailService.sendSimpleMessage(annotation.to(), annotation.subject(), view.toText());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
