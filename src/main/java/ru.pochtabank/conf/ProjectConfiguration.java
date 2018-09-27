package ru.pochtabank.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.pochtabank.process.BankSendingProcess;
import ru.pochtabank.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class ProjectConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectConfiguration.class.getName());
    private String registryFolder;

    public void init() {
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("configuration.properties");
            properties.load(resourceAsStream);
            registryFolder = properties.getProperty("file_path");
            resourceAsStream.close();
        } catch (IOException e) {
            LOGGER.error("Cannot find configuration file");
            e.printStackTrace();
        }
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FileService fileService() {
        return new FileService(registryFolder);
    }
}
