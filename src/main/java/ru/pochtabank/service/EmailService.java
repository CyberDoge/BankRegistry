package ru.pochtabank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final MimeMessageHelper messageHelper;

    @Autowired
    public EmailService(JavaMailSender mailSender, MimeMessageHelper messageHelper) {
        this.mailSender = mailSender;
        this.messageHelper = messageHelper;
    }

    @Async
    public void sendError(String errorMessage, File zip) throws MessagingException {
        messageHelper.addAttachment("registry", zip);
        messageHelper.setText(errorMessage);
        mailSender.send(messageHelper.getMimeMessage());
        zip.delete();
    }
}
