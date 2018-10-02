package ru.pochtabank.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URI;
import java.util.UUID;

public class RegistrySender {
    private final URI hostUri;
    private RestTemplate restTemplate;

    @Autowired
    public RegistrySender(RestTemplate restTemplate, String host) {
        this.restTemplate = restTemplate;
        hostUri = URI.create(host);
    }

    public ResponseEntity<UUID> sendRegistryToBank(byte[] registry) {
        return restTemplate.postForEntity(hostUri, registry, UUID.class);
    }

}
