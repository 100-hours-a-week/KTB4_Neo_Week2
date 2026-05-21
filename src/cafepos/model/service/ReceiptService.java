package cafepos.model.service;

import cafepos.model.domain.order.OrderItem;
import cafepos.model.domain.store.StoreInfo;
import cafepos.model.infra.receipt.ReceiptBuilder;
import cafepos.model.infra.receipt.ReceiptWriter;

import java.time.LocalDateTime;
import java.util.List;

public class ReceiptService {
    private final ReceiptBuilder receiptBuilder;
    private final ReceiptWriter receiptWriter;
    private final StoreInfo storeInfo;

    public ReceiptService(ReceiptBuilder receiptBuilder, ReceiptWriter receiptWriter, StoreInfo storeInfo) {
        this.receiptBuilder = receiptBuilder;
        this.receiptWriter = receiptWriter;
        this.storeInfo = storeInfo;
    }

    public void createReceipt(List<OrderItem> items, int totalPrice) {
        LocalDateTime paidAt = LocalDateTime.now();
        String content = receiptBuilder.build(items, totalPrice, paidAt, storeInfo);
        receiptWriter.write(content, paidAt);
    }
}
