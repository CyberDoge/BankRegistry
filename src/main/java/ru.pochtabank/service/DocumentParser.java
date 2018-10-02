package ru.pochtabank.service;

import org.jamel.dbf.processor.DbfProcessor;
import ru.pochtabank.model.BankRegistry;
import ru.pochtabank.model.ClientRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DocumentParser {
    public static List<ClientRegistry> parseDbfFile(File dbf) throws FileNotFoundException {
        if (dbf == null) throw new FileNotFoundException("no dfb file in folder");
        return DbfProcessor.loadData(dbf, (row) -> {
            return new ClientRegistry((String) row[0], ((String) row[1]), ((float) row[2]), ((float) row[3]), ((float) row[4]), ((String) row[5]), ((String) row[6]), ((String) row[7]), ((String) row[8]), ((char) row[9]), ((String) row[10]), ((String) row[11]), ((String) row[12]));
        });
    }

    public static byte[][] saveBankRegister(List<BankRegistry> bankRegistries, File bankSaveFile) {
        byte[][] result = new byte[bankRegistries.size()][];
        //todo fucking atomic
        AtomicInteger i = new AtomicInteger();
        bankRegistries.forEach(registry -> {
            var line = new StringBuilder(registry.getPersonalAccount()).append(';')
                    .append(registry.getPaymentPeriod()).append(';').append(registry.getChargesAmount());
            try (var streamWriter = new FileOutputStream(bankSaveFile)) {
                var bytes = line.toString().getBytes();
                result[i.get()] = bytes;
                i.getAndIncrement();
                streamWriter.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}
