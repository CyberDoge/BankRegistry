package ru.pochtabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class.getName());
    //folder with registries files
    private final File registrySendFolder;
    private final File registryGetFolder;
    private final File statusFolder;


    @Autowired
    public FileService(@Value("${send_registry}") String registrySendFolder, @Value("${get_registry}") String registryGetFolder, @Value("${status_registry}") String statusFolder) throws IOException {

        //if value empty registries will be store in OS home folder
        if (registrySendFolder == null || registrySendFolder.isEmpty()) {
            registrySendFolder = System.getProperty("user.home") + File.pathSeparator + "registries";
        }
        if (registryGetFolder == null || registryGetFolder.isEmpty()) {
            registryGetFolder = System.getProperty("user.home") + File.pathSeparator + "received";
        }
        if (statusFolder == null || statusFolder.isEmpty()) {
            statusFolder = System.getProperty("user.home") + File.pathSeparator + "status";
        }
        this.registryGetFolder = new File(registryGetFolder);
        this.statusFolder = new File(statusFolder);
        this.registrySendFolder = new File(registrySendFolder);
        if (!this.registryGetFolder.exists()) this.registryGetFolder.mkdirs();
        if (!this.statusFolder.exists()) this.statusFolder.mkdirs();
        if (!this.registrySendFolder.exists()) this.registrySendFolder.mkdirs();

    }

    public File getRegistrySendFolder() {
        return registrySendFolder;
    }

    public void successMoveFile(String pathStr) throws IOException {
        var path = Paths.get(pathStr);
        var successPath = Paths.get(registrySendFolder.getAbsolutePath() + File.pathSeparator + "Send" + File.pathSeparator + "Success");
        Files.createDirectories(successPath);
        Files.move(path, successPath);
    }

    public void sendErrorMoveFile(String pathStr) throws IOException {
        var path = Paths.get(pathStr);
        var errorPath = Paths.get(registrySendFolder.getAbsolutePath() + File.pathSeparator + "SendError");
        Files.createDirectories(errorPath);
        Files.move(path, errorPath);
    }


    public void errorMoveFile(String pathStr) throws IOException {
        var path = Paths.get(pathStr);
        var errorPath = Paths.get(registrySendFolder.getAbsolutePath() + File.pathSeparator + "Send" + File.pathSeparator + "Error");
        Files.createDirectories(errorPath);
        Files.move(path, errorPath);
    }

    public File[] getClientRegistryFiles() throws IOException {
        //check if file has suitable name
        return registrySendFolder.listFiles((file, s) -> s.matches("^[A-Za-z]{3}\\d{4}\\.dbf$"));
    }
}
