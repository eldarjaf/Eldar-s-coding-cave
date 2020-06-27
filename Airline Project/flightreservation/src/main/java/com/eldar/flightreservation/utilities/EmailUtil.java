package com.eldar.flightreservation.utilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender sender;

    @Value("${com.eldar.flightreservation.itinerary.email.subject}")
    private String EMAIL_SUBJECT;

    @Value("${com.eldar.flightreservation.itinerary.email.body}")
    private String EMAIL_BODY;


    public void sendItinerary(String toAddress, String filePath) {
        log.info("Inside sendItinerary()");
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);//because we have attachment
            helper.setTo(toAddress);
            helper.setSubject(EMAIL_SUBJECT);
            helper.setText(EMAIL_BODY);
            helper.addAttachment("Itinerary", new File(filePath));
            sender.send(message);
        } catch (MessagingException e) {
            log.error("Exception inside sendItinerary: " + e);
        }
    }
}
