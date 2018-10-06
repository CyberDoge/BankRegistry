package ru.pochtabank.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class RegistrySender {
    private final URI hostUri;
    private RestTemplate restTemplate;

    @Autowired
    public RegistrySender(RestTemplate restTemplate, String host) {
        this.restTemplate = restTemplate;
        hostUri = URI.create(host);
    }

    public ResponseEntity<String> sendRegistryToBank(String registryPath) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(registryPath));
        var requestEntity = new HttpEntity<>(body, headers);
        //todo uuid
        return restTemplate.postForEntity(hostUri + "/client-to-bank", requestEntity, String.class);
    }

}
