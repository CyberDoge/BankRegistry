package ru.pochtabank.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BankSendingProcess {
    private final RestTemplate restTemplate;

    @Autowired
    public BankSendingProcess(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void sendRegistryToBank() {

    }
}
