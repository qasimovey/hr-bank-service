package org.qasimovey.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
/*
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
*/

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }


/*  might be for future
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("user", "ajeje");
            requestTemplate.header("password", "brazof");
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
        };
    }
    */
}
