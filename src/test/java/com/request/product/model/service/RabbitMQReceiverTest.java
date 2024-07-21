package com.request.product.model.service;

import com.request.product.service.RabbitMQReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RabbitMQReceiverTest {

    @InjectMocks
    private RabbitMQReceiver receiver;

    @Test
    public void receiveWithoutRequest() {
        Assertions.assertThrows(Exception.class, ()->receiver.receive(null));
    }
}
