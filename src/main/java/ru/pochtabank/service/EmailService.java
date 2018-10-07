package ru.pochtabank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;

@Service
public class EmailService {
    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Async
    public void sendError(SimpleMailMessage email, File file, String errorMessage) throws IOException {
        URI uri = URI.create("jar:file:/error_file.zip");
        var env = new HashMap<String, String>();
        env.put("create", "true");
        System.out.println(uri);
        try (FileSystem zipf = FileSystems.newFileSystem(uri, env)) {
            Path pathInZipFile = zipf.getPath(File.separator + file.getName());
            Files.copy(file.toPath(), pathInZipFile, StandardCopyOption.REPLACE_EXISTING);
        }
        mailSender.send(email);
    }
}
