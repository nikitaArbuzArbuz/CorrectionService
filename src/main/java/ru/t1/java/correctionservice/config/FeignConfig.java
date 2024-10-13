package ru.t1.java.correctionservice.config;

import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.t1.java.correctionservice.feign.decoder.FeignErrorDecoder;

@EnableScheduling
@EnableFeignClients("ru.t1.java.correctionservice")
@Configuration
public class FeignConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}
