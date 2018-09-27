package ru.pochtabank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ru.pochtabank.conf.ProjectConfiguration;
import ru.pochtabank.process.BankSendingProcess;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration();
        projectConfiguration.init();
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public CommandLineRunner runner(BankSendingProcess bankSendingProcess) {
        return (args) -> {
            bankSendingProcess.getRestTemplate();
        };
    }
//    @Override
//    public void run(String... args) {
//
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
//        applicationContext.getBean(RestTemplate.class);
//    }
}
