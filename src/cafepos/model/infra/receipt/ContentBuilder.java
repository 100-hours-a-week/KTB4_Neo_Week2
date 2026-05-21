package cafepos.model.infra.receipt;

import cafepos.model.domain.order.OrderItem;
import cafepos.model.domain.store.StoreInfo;

import java.time.LocalDateTime;
import java.util.List;

public class ContentBuilder implements ReceiptBuilder {
    @Override
    public String build(List<OrderItem> items, int totalPrice, LocalDateTime paidAt, StoreInfo storeInfo) {
        StringBuilder sb = new StringBuilder();

        appendHeader(sb, paidAt, storeInfo);
        appendBody(sb, items);
        appendFooter(sb, totalPrice);

        return sb.toString();
    }

    private void appendHeader(StringBuilder sb, LocalDateTime paidAt, StoreInfo storeInfo) {
        sb.append(ReceiptText.THICK_LINE).append('\n').append('\n');
        sb.append("             ").append(ReceiptText.TITLE).append('\n').append('\n');
        sb.append(ReceiptText.THICK_LINE).append('\n');
        appendField(sb, ReceiptText.LABEL_STORE, storeInfo.getStoreName());
        appendField(sb, ReceiptText.LABEL_OWNER, storeInfo.getOwnerName());
        appendField(sb, ReceiptText.LABEL_BIZ_NO, storeInfo.getBusinessNumber());
        appendField(sb, ReceiptText.LABEL_PHONE, storeInfo.getTelephone());
        appendField(sb, ReceiptText.LABEL_ADDRESS, storeInfo.getAddress());
        sb.append(ReceiptText.THIN_LINE).append('\n');
        appendField(sb, ReceiptText.LABEL_PAID_AT, paidAt.format(ReceiptFormat.DISPLAY_TIME));
        sb.append(ReceiptText.THICK_LINE).append('\n');
    }

    private void appendBody(StringBuilder sb, List<OrderItem> items) {
        for (OrderItem item : items) {
            sb.append(' ')
                    .append(item.getMenuData().getName())
                    .append("  ")
                    .append(item.getQuantity())
                    .append("  ")
                    .append(item.getItemPrice())
                    .append(ReceiptText.CURRENCY)
                    .append('\n');

            String option = item.getOption();
            if (!option.isBlank()) {
                sb.append("    (").append(option).append(")").append('\n');
            }
            sb.append('\n');
        }
    }

    private void appendFooter(StringBuilder sb, int totalPrice) {
        sb.append(ReceiptText.THIN_LINE).append('\n');
        appendField(sb, ReceiptText.LABEL_TOTAL, totalPrice + ReceiptText.CURRENCY);
        sb.append(ReceiptText.THICK_LINE).append('\n');
        appendField(sb, ReceiptText.LABEL_THREAD, Thread.currentThread().getName());
    }

    private void appendField(StringBuilder sb, String label, String value) {
        sb.append(' ').append(label).append(" : ").append(value).append('\n');
    }
}
