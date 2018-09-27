package ru.pochtabank.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.pochtabank.service.FileService;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class ProjectConfiguration {
    private static final Logger LOGGER = Logger.getLogger(ProjectConfiguration.class.getName());
    private String registryFolder;

    public void init() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("configuration.properties"));
            registryFolder = properties.getProperty("file_path");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Cannot find configuration file");
            e.printStackTrace();
        }
    }

    @Bean
    public FileService fileService() {
        return new FileService(registryFolder);
    }
}
