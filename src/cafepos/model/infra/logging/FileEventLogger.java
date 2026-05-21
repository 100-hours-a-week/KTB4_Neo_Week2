package cafepos.model.infra.logging;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileEventLogger implements EventLogger {
    private static final Path LOG_DIR = Path.of("logs");
    private static final Path LOG_FILE = LOG_DIR.resolve("pos.log.txt");

    @Override
    public void append(String line) {
        try {
            Files.createDirectories(LOG_DIR);
            Files.writeString(
                    LOG_FILE,
                    line + System.lineSeparator(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("로그 저장 실패: " + e.getMessage());
        }
    }
}
