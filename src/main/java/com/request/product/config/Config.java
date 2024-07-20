package com.request.product.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

import java.util.Objects;

public class Config {

    @Getter
    private String email;
    @Getter
    private String password;

    public Config(){
        Dotenv dotenv = Dotenv.configure().load();
        this.email = dotenv.get("EMAIL_SENDER");
        this.password = dotenv.get("EMAIL_SENDER_PASSWORD");
    }

}
