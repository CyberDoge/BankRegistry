package ru.pochtabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class.getName());
    //folder with registries files
    private final String registryFolder;

    public FileService(String registryFolder) {
        //if value empty registries will be store in OS home folder
        if (registryFolder == null || registryFolder.isEmpty()) {
            registryFolder = System.getProperty("user.home") + File.pathSeparator + "registries";
        }
        this.registryFolder = registryFolder;
        //todo check if dir empty
    }

    public String getRegistryFolder() {
        return registryFolder;
    }

    public File[] getClientRegistryFiles() {
        //check if file has suitable name
        return new File(registryFolder).listFiles((file, s) -> s.matches("^[A-Za-z]{3}\\d{4}\\.dbf$"));

    }
}
