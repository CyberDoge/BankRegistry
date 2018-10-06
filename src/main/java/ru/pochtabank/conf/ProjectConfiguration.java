package ru.pochtabank.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectConfiguration.class.getName());

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //todo host
    @Bean
    public String host() {
        return "http://localhost:8080";
    }

}
