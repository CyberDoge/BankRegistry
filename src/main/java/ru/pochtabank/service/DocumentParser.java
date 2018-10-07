package ru.pochtabank.service;

import org.jamel.dbf.processor.DbfProcessor;
import org.jamel.dbf.utils.DbfUtils;
import ru.pochtabank.model.BankRegistry;
import ru.pochtabank.model.ClientRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

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

    public static String saveBankRegister(List<BankRegistry> bankRegistries) {
//        var result = new ArrayList<String>(bankRegistries.size());
        var line = new StringBuilder();
        bankRegistries.forEach(registry -> {
            line.append(registry.getPersonalAccount()).append(';')
                    .append(registry.getPaymentPeriod()).append(';').append(registry.getChargesAmount()).append('\n');
            //todo ask normal name and creating folder
//            var fileName = bankSaveParentRoot + "/" + UUID.randomUUID();
//            var file = new File(fileName);
//            file.getParentFile().mkdirs();
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try (var streamWriter = new FileOutputStream(file)) {
//                streamWriter.write(line.toString().getBytes());
//                result.add(fileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        });
        return line.toString();
    }
}
