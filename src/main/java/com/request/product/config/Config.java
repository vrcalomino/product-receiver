package com.request.product.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

@Getter
public class Config {

    private final String email;
    private final String password;

    public Config(){
        Dotenv dotenv = Dotenv.configure().load();
        this.email = dotenv.get("EMAIL_SENDER");
        this.password = dotenv.get("EMAIL_SENDER_PASSWORD");
    }

}
