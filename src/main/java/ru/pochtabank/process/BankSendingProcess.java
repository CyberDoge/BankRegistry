package ru.pochtabank.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pochtabank.exchange.RegistrySender;
import ru.pochtabank.service.DocumentParser;
import ru.pochtabank.service.FileService;
import ru.pochtabank.service.ModelConverter;

import java.io.File;
import java.io.IOException;

@Component
public class BankSendingProcess {
    private final FileService fileService;
    private final RegistrySender registrySender;

    @Autowired
    public BankSendingProcess(FileService fileService, RegistrySender registrySender) {
        this.fileService = fileService;
        this.registrySender = registrySender;
    }

    public void sendRegistryToBank() throws IOException {
        var files = fileService.getClientRegistryFiles();
        for (File file : files) {
            var clientRegistries = DocumentParser.parseDbfFile(file);
            var bankRegistries = ModelConverter.convertFromClientToBank(clientRegistries);
            var bankRegistryLines = DocumentParser.saveBankRegister(bankRegistries);
            var responseEntity = registrySender.sendRegistryToBank(bankRegistryLines);
            try {
                if (responseEntity.getStatusCode().value() == 200) {
                    fileService.successMoveFile(file);
                } else fileService.errorMoveFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
