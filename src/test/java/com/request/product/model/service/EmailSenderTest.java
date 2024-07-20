package com.request.product.model.service;

import com.request.product.model.Product;
import com.request.product.service.EmailSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;

public class EmailSenderTest {

    private static EmailSender emailSender;

    @BeforeAll
    public static void loadEmailSender(){
        emailSender = Mockito.mock(EmailSender.class);
    }

    @Test
    public void missingReceiver() throws MessagingException {
        Mockito.doThrow(MessagingException.class)
                .when(emailSender)
                .sendEmail(Mockito.isNull(), Mockito.anyString(), Mockito.any());

        MessagingException exception = Assertions.assertThrows(MessagingException.class, () -> {
            emailSender.sendEmail(null, "smtp.gmail.com", new Product("Product", 200, "A product", "img.com"));
        });
        assertNull(exception.getMessage());
    }

    @Test
    public void missingHost() throws MessagingException {
        Mockito.doThrow(MessagingException.class)
                .when(emailSender)
                .sendEmail(Mockito.anyString(), Mockito.isNull(), Mockito.any());

        MessagingException exception = Assertions.assertThrows(MessagingException.class, () -> {
            emailSender.sendEmail("mail@gmail.com", null, new Product("Product", 200, "A product", "img" +
                    ".com"));
        });
        assertNull(exception.getMessage());
    }

    @Test
    public void missingProduct() throws MessagingException {
        Mockito.doThrow(MessagingException.class)
                .when(emailSender)
                .sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.isNull());

        MessagingException exception = Assertions.assertThrows(MessagingException.class, () -> {
            emailSender.sendEmail("mail@gmail.com", "smtp.gmail.com", null);
        });
        assertNull(exception.getMessage());
    }

    @Test
    public void correctData() throws MessagingException {
        Mockito.doNothing()
                .when(emailSender)
                .sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.any());

        Assertions.assertDoesNotThrow(() -> emailSender.sendEmail("mail@gmail.com", "smtp.gmail" +
                ".com", new Product("Product", 200, "A product", "img" +
                ".com")));
    }
}
