package ru.pochtabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
            registrySendFolder = System.getProperty("user.home") + File.separatorChar + "registries";
        }
        if (registryGetFolder == null || registryGetFolder.isEmpty()) {
            registryGetFolder = System.getProperty("user.home") + File.separatorChar + "received";
        }
        if (statusFolder == null || statusFolder.isEmpty()) {
            statusFolder = System.getProperty("user.home") + File.separatorChar + "status";
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

    public void successMoveFile(File file) {
        var successPath = new File(statusFolder.getAbsolutePath() + File.separatorChar + "Send"
                + File.separatorChar + "Success" + File.separatorChar + file.getName());
        successPath.getParentFile().mkdirs();
        file.renameTo(successPath);
    }

    public void sendErrorMoveFile(File file) {
        var errorPath = new File(statusFolder.getAbsolutePath() + File.separatorChar + "SendError"
                + File.separatorChar + file.getName());
        errorPath.getParentFile().mkdirs();
        file.renameTo(errorPath);
    }


    public void errorMoveFile(File file) {
        var errorPath = new File(statusFolder.getAbsolutePath() + File.separatorChar + "Send" + File.separatorChar + "Error"
                + File.separatorChar + file.getName());
        errorPath.getParentFile().mkdirs();
        file.renameTo(errorPath);
    }

    public File createZipFile(File file) {
        File zipF = new File(file.getPath() + ".zip");
        try (var zos = new ZipOutputStream(new FileOutputStream(zipF))) {
            ZipEntry entry = new ZipEntry(file.getName());
            zos.putNextEntry(entry);
            zos.closeEntry();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return zipF;

    }

    public File[] getClientRegistryFiles() {
        //check if file has suitable name
        return registrySendFolder.listFiles((file, s) -> s.matches("^[A-Za-z]{3}\\d{4}\\.dbf$"));
    }
}
