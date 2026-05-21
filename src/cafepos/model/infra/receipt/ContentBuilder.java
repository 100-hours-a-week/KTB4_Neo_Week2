package cafepos.model.infra.receipt;

import cafepos.model.domain.order.OrderItem;
import cafepos.model.domain.store.StoreInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ContentBuilder implements ReceiptBuilder{
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String build(List<OrderItem> items, int totalPrice, LocalDateTime paidAt, StoreInfo storeInfo) {
        StringBuilder sb = new StringBuilder();

        sb.append("===================================\n\n");
        sb.append("             영수증\n\n");
        sb.append("===================================\n");
        sb.append(" 상호명 : ").append(storeInfo.getStoreName()).append("\n");
        sb.append(" 대표자 : ").append(storeInfo.getOwnerName()).append("\n");
        sb.append(" 사업자 번호 ").append(storeInfo.getBusinessNumber()).append("\n");
        sb.append(" 전화번호 : ").append(storeInfo.getTelephone()).append("\n");
        sb.append(" 주소 : ").append(storeInfo.getAddress()).append("\n");
        sb.append("-----------------------------------\n");
        sb.append(" 결제 시각 : ").append(paidAt.format(TIME)).append("\n");
        sb.append("===================================\n");

        for(OrderItem item : items) {
            sb.append(" ");
            sb.append(item.getMenuData().getName())
                    .append("  ").append(item.getQuantity())
                    .append("  ").append(item.getItemPrice()).append("원\n");

            String option = item.getOption();
            if(!option.isBlank()) {
                sb.append("    (").append(option).append(")\n");
            }
            sb.append("\n");
        }

        sb.append("\n\n-----------------------------------\n");
        sb.append("총 결제 금액 :    ").append(totalPrice).append("원\n");
        sb.append("===================================\n");

        return sb.toString();
    }
}
