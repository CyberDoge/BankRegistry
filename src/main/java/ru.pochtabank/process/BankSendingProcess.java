package ru.pochtabank.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pochtabank.exchange.RegistrySender;
import ru.pochtabank.service.DocumentParser;
import ru.pochtabank.service.FileService;
import ru.pochtabank.service.ModelConverter;

import java.io.File;
import java.io.FileNotFoundException;

@Component
public class BankSendingProcess {
    private final FileService fileService;
    private final RegistrySender registrySender;

    @Autowired
    public BankSendingProcess(FileService fileService, RegistrySender registrySender) {
        this.fileService = fileService;
        this.registrySender = registrySender;
    }

    public void sendRegistryToBank() throws FileNotFoundException {
        var files = fileService.getClientRegistryFiles();
        for (File file : files) {
            var clientRegistries = DocumentParser.parseDbfFile(file);
            var bankRegistries = ModelConverter.convertFromClientToBank(clientRegistries);
            //todo
            var stream = DocumentParser.saveBankRegister(bankRegistries, new File(fileService.getRegistryFolder()+"/bank"));
            for (byte[] bytes : stream) {
                registrySender.sendRegistryToBank(bytes);
            }
        }
    }
}
