package cafepos.controller.action;

import cafepos.model.infra.async.AsyncTaskManager;
import cafepos.model.service.LogService;
import cafepos.model.service.PaymentService;
import cafepos.model.domain.shoppingcart.ShoppingCart;
import cafepos.model.service.ReceiptService;
import cafepos.view.InputView;
import cafepos.view.OutputView;

public class ActionContext {
    private final ShoppingCart cart;
    private final InputView inputView;
    private final OutputView outputView;
    private final PaymentService paymentService;
    private final ReceiptService receiptService;
    private final LogService logService;
    private final AsyncTaskManager asyncTaskManager;

    private boolean running = true;

    public ActionContext(ShoppingCart cart,
                         InputView inputView,
                         OutputView outputView,
                         PaymentService paymentService,
                         ReceiptService receiptService,
                         LogService logService,
                         AsyncTaskManager asyncTaskManager) {
        this.cart = cart;
        this.inputView = inputView;
        this.outputView = outputView;
        this.paymentService = paymentService;
        this.receiptService = receiptService;
        this.logService = logService;
        this.asyncTaskManager = asyncTaskManager;
    }

    public ShoppingCart cart() { return cart; }
    public InputView inputView() { return inputView; }
    public OutputView outputView() { return outputView; }
    public PaymentService paymentService() { return paymentService; }
    public ReceiptService receiptService() { return receiptService; }
    public LogService logService() { return logService; }
    public AsyncTaskManager asyncTaskManager() { return asyncTaskManager; }

    public boolean isRunning() { return running; }
    public void stop() { this.running = false; }
}
