package ru.pochtabank.service;

import java.io.File;

public class FileService {
    //folder with registries files
    private final String registryFolder;

    public FileService(String registryFolder) {
        //if value empty registries will be store in OS home folder
        if (registryFolder == null || registryFolder.isEmpty()) {
            registryFolder = System.getProperty("user.home") + File.pathSeparator + "registries";
        }
        this.registryFolder = registryFolder;
    }
}
