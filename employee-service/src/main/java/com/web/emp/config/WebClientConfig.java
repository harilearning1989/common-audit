package com.web.emp.config;

import com.web.emp.services.client.JsonPlaceHolderClient;
import com.web.util.config.CommonWebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class WebClientConfig {

    @Value("${login.rest.jsonPlaceHolder}")
    private String jsonPlaceHolder;

    @Bean
    public JsonPlaceHolderClient jsonPlaceHolderClient() {
        Map<String, String> headers = Map.of(
                "Authorization", "Bearer your-token",
                "Custom-Header", "CustomValue"
        );
        return new CommonWebClient()
                .httpServiceProxyFactory(jsonPlaceHolder, headers, JsonPlaceHolderClient.class);
    }
}
