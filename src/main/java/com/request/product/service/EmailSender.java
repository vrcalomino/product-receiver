package com.request.product.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Properties;

@Service
public class EmailSender {

    public void sendEmail(String receiver, String host, String productInfo) {
        Dotenv dotenv = Dotenv.configure().load();

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);

        String sender = Objects.requireNonNull(dotenv.get("EMAIL_SENDER"));
        String password = Objects.requireNonNull(dotenv.get("EMAIL_SENDER_PASSWORD"));
        System.out.println(password);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            message.setSubject("Product Information Requested");

            message.setText(productInfo);

            Transport.send(message);
        } catch (MessagingException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
