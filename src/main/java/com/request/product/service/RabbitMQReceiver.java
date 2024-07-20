package com.request.product.service;

import com.request.product.config.RabbitMQConfig;
import com.request.product.model.RequestInformation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

    private final EmailSender emailSender;

    public RabbitMQReceiver(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void receive(RequestInformation productRequest) {
        System.out.println("Product read...");
        emailSender.sendEmail(productRequest.getEmail(), "smtp.gmail.com", "A product...");
    }
}
