package cafepos.model.infra.receipt;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileWriter implements ReceiptWriter {
    private static final Path RECEIPT_DIR = Path.of("receipts");
    private static final DateTimeFormatter FILENAME_TIME = DateTimeFormatter.ofPattern("yyMMdd_HHmmss");

    @Override
    public void write(String content, LocalDateTime paidAt) {
        String fileName = "receipt_" + paidAt.format(FILENAME_TIME) + ".txt";
        Path receiptPath = RECEIPT_DIR.resolve(fileName);

        try {
            Files.createDirectories(RECEIPT_DIR);
            Files.writeString(receiptPath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("영수증 저장 실패 : " + e.getMessage());
        }
    }
}
