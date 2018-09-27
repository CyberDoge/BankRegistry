package ru.pochtabank;

import org.springframework.web.client.RestTemplate;
import ru.pochtabank.conf.ProjectConfiguration;


public class Application {
    public static void main(String[] args) {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration();
        projectConfiguration.init();
        RestTemplate restTemplate = new RestTemplate();
    }
}
