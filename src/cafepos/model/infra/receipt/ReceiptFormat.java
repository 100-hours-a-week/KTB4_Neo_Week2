package cafepos.model.infra.receipt;

import java.time.format.DateTimeFormatter;

public final class ReceiptFormat {
    private ReceiptFormat() {
    }

    public static final DateTimeFormatter DISPLAY_TIME =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
