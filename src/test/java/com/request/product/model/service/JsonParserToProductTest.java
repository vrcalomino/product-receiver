package com.request.product.model.service;

import com.request.product.model.Product;
import com.request.product.service.JsonParserToProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JsonParserToProductTest {

    @InjectMocks
    private static JsonParserToProduct parser;


    @Test
    public void noJsonString() {
        Assertions.assertThrows(Exception.class, ()-> parser.ParseToProduct(null));
    }

    @Test
    public void invalidJsonString() throws Exception {
        String invalidJson = "{title:\"Product\",price:26";
        Assertions.assertThrows(Exception.class, () -> parser.ParseToProduct(invalidJson));
    }

    @Test
    public void validJsonString() throws Exception {
        String validJson = "{\"title\":\"Product\",\"price\":100,\"description\":\"A product...\",\"image\":\"image" +
                ".com\"}";
        Product product = parser.ParseToProduct(validJson);
        Assertions.assertEquals(new Product("Product", 100, "A product...", "image.com"), product);
    }
}
