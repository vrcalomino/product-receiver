package com.request.product.model.service;

import com.request.product.service.ProductAPIFetcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductAPIFetcherTest {

    @InjectMocks
    private ProductAPIFetcher productFetcher;

    @Test
    public void fetchSingleProductWithoutId(){
        Assertions.assertThrows(Exception.class, () -> productFetcher.fetchSingleProduct(null));
    }
}
