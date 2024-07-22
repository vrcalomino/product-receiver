package com.request.product.model.service;

import com.request.product.service.ProductAPIFetcher;
import com.request.product.service.RabbitMQReceiver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RabbitMQReceiverTest {

    @InjectMocks
    private RabbitMQReceiver receiver;

    @Mock
    private ProductAPIFetcher productAPIFetcher;

    @Test
    public void receiveWithoutRequest() throws Exception {
        receiver.receive(null);

        verify(productAPIFetcher, Mockito.never()).fetchSingleProduct(Mockito.anyLong());
    }
}
