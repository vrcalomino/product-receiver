package com.request.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.request.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class JsonParserToProduct {

    public Product ParseToProduct(String jsonString) throws Exception {
        if (jsonString == null) {
            throw new Exception("No json string provided");
        }
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonString);

            String title = rootNode.path("title").asText();
            double price = rootNode.path("price").asDouble();
            String description = rootNode.path("description").asText();
            String image = rootNode.path("image").asText();

            return new Product(title, price, description, image);

    }
}
