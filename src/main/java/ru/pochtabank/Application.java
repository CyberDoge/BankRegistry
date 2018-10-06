package ru.pochtabank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import ru.pochtabank.process.BankSendingProcess;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public CommandLineRunner runner(BankSendingProcess bankSendingProcess) {
        return (args) -> {
            bankSendingProcess.sendRegistryToBank();
        };
    }
}
