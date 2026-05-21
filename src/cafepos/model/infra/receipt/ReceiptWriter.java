package cafepos.model.infra.receipt;

import java.time.LocalDateTime;

public interface ReceiptWriter {
    void write(String content, LocalDateTime paidAt);
}
