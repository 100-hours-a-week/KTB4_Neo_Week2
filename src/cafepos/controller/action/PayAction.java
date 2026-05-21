package cafepos.controller.action;

import cafepos.model.domain.order.OrderItem;
import java.util.ArrayList;
import java.util.List;

public class PayAction implements MainAction {
    private final ActionContext ctx;

    public PayAction(ActionContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void action() {
        if (!ctx.paymentService().canPay(ctx.cart())) {
            ctx.outputView().printPaymentFailed();
            return;
        }

        int totalPrice = ctx.paymentService().calculatePaymentPrice(ctx.cart());
        ctx.outputView().printPaymentPrice(totalPrice);

        boolean confirm = ctx.inputView().readPaymentConfirm();
        boolean paid = ctx.paymentService().processPayment(confirm);

        if (paid) {
            List<OrderItem> paidItems = new ArrayList<>(ctx.cart().getItems());
            ctx.outputView().printPaymentCompleted();
            ctx.receiptService().createReceipt(paidItems, totalPrice);
            ctx.logService().logPayment(totalPrice);
            ctx.cart().clear();
        }
        else {
            ctx.outputView().printPaymentCanceled();
        }
    }
}
