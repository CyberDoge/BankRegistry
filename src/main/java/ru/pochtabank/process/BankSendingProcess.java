package ru.pochtabank.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import ru.pochtabank.exchange.RegistrySender;
import ru.pochtabank.service.DocumentParser;
import ru.pochtabank.service.EmailService;
import ru.pochtabank.service.FileService;
import ru.pochtabank.service.ModelConverter;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;

@Component
public class BankSendingProcess {
    private final FileService fileService;
    private final RegistrySender registrySender;
    private final EmailService emailService;

    @Autowired
    public BankSendingProcess(FileService fileService, RegistrySender registrySender, EmailService emailService) {
        this.fileService = fileService;
        this.registrySender = registrySender;
        this.emailService = emailService;
    }

    public void sendRegistryToBank() throws IOException, MessagingException {
        var files = fileService.getClientRegistryFiles();
        for (File file : files) {
            var clientRegistries = DocumentParser.parseDbfFile(file);
            var bankRegistries = ModelConverter.convertFromClientToBank(clientRegistries);
            var bankRegistryLines = DocumentParser.saveBankRegister(bankRegistries);
            var responseEntity = registrySender.sendRegistryToBank(bankRegistryLines);
            if (responseEntity.getStatusCode().value() == 200) {
                fileService.successMoveFile(file);
            } else {
                var zip = fileService.createZipFile(file);
                emailService.sendError(responseEntity.getBody(), zip);
                fileService.errorMoveFile(file);
            }

        }
    }
}
