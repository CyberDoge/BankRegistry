package ru.pochtabank.service;

import org.jamel.dbf.processor.DbfProcessor;
import org.jamel.dbf.utils.DbfUtils;
import ru.pochtabank.model.BankRegistry;
import ru.pochtabank.model.ClientRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DocumentParser {
    public static List<ClientRegistry> parseDbfFile(File dbf) throws FileNotFoundException {
        if (dbf == null) throw new FileNotFoundException("no dfb file in folder");
        return DbfProcessor.loadData(dbf, (row) -> new ClientRegistry(bytesToString(row[0]),
                bytesToString(row[1]), ((Double) row[2]).floatValue(), ((Double) row[3]).floatValue(),
                ((Double) row[4]).floatValue(), bytesToString(row[5]), bytesToString(row[6]),
                bytesToString(row[7]), bytesToString(row[8]), (((char) ((byte[]) (row[9]))[0])),
                bytesToString(row[10]), bytesToString(row[11]), bytesToString(row[12])));
    }

    private static String bytesToString(Object row) {
        return new String(DbfUtils.trimLeftSpaces((byte[]) row));
    }

    public static List<String> saveBankRegister(List<BankRegistry> bankRegistries, File bankSaveParentRoot) {
        var result = new ArrayList<String>(bankRegistries.size());
        bankRegistries.forEach(registry -> {
            var line = new StringBuilder(registry.getPersonalAccount()).append(';')
                    .append(registry.getPaymentPeriod()).append(';').append(registry.getChargesAmount());
            //todo ask normal name and creating folder
            var fileName = bankSaveParentRoot + "/" + UUID.randomUUID();
            var file = new File(fileName);
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (var streamWriter = new FileOutputStream(file)) {
                streamWriter.write(line.toString().getBytes());
                result.add(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}
