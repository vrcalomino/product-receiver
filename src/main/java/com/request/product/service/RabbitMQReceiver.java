package com.request.product.service;

import com.request.product.config.RabbitMQConfig;
import com.request.product.model.Product;
import com.request.product.model.RequestInformation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class RabbitMQReceiver {

    private final EmailSender emailSender;
    private final ProductAPIFetcher productAPIFetcher;
    private final JsonParserToProduct parser;

    public RabbitMQReceiver(EmailSender emailSender, ProductAPIFetcher productAPIFetcher, JsonParserToProduct parser) {
        this.emailSender = emailSender;
        this.productAPIFetcher = productAPIFetcher;
        this.parser = parser;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void receive(RequestInformation productRequest) throws Exception {
        if (productRequest == null) {
            throw new Exception("No request information");
        }
        String productInfo = productAPIFetcher.fetchSingleProduct(productRequest.getProduct_id());
        Product product = parser.ParseToProduct(productInfo);
        try {
            emailSender.sendEmail(productRequest.getEmail(), "smtp.gmail.com", product);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
