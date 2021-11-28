package io.saud.vending.utils;

import io.saud.vending.model.TransactionDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

public class TransactionLogUtils {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "id,item,amount,date";
    private static final String FILE_PATH = "transaction-logs/transactions-%s.csv";

    private static Logger logger = Logger.getLogger(TransactionLogUtils.class.getName());

    public static void logTransactions(TransactionDTO dto) {
        FileWriter fileWriter = null;
        boolean isFileExist = false;
        String filePath = String.format(FILE_PATH, DateUtils.parseDateToString(LocalDate.now()));
        try {

            isFileExist = Files.exists(Path.of(filePath));
            fileWriter = new FileWriter(filePath, true);
            if (!isFileExist) {
                fileWriter.append(FILE_HEADER.toString());
            }
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(dto.getId().toString());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(dto.getItem());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(dto.getAmount().toString());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(dto.getTransactionDate().toString());

            logger.info("Transaction has been logged successfully");
        } catch (Exception e) {
            logger.severe("Error while logging transaction");
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                logger.severe("Error while flushing/closing fileWriter");
            }
        }
    }

}
