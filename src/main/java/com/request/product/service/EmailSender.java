package com.request.product.service;

import com.request.product.config.Config;
import com.request.product.model.Product;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Objects;
import java.util.Properties;

@Service
public class EmailSender {

    public void sendEmail(String receiver, String host, Product product) throws MessagingException {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);

        Config config = new Config();
        String sender = config.getEmail();
        String password = config.getPassword();

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });


            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            message.setSubject("Product Information Requested");

            MimeBodyPart messageBodyPart = new MimeBodyPart();

            String htmlContent = String.format("<h2>%s</h2>\n<p>%s</p>\n<p>%.2f</p>\n<p>Thank you " +
                            "for asking</p>", product.getTitle(),
                    product.getDescription(), product.getPrice());

            messageBodyPart.setContent(htmlContent, "text/html");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
    }
}
