package cafepos.model.infra.receipt;

import java.time.LocalDateTime;
import java.util.List;

import cafepos.model.domain.order.OrderItem;
import cafepos.model.domain.store.StoreInfo;

public interface ReceiptBuilder {
    String build(List<OrderItem> items, int totalPrice, LocalDateTime paidAt, StoreInfo storeInfo);
}
