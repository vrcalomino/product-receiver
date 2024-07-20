package com.request.product.service;

import com.request.product.config.RabbitMQConfig;
import com.request.product.model.RequestInformation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void receive(RequestInformation productRequest) {
        System.out.println("Received product: " + productRequest.getProduct_id() + ", " + productRequest.getEmail());
    }
}
