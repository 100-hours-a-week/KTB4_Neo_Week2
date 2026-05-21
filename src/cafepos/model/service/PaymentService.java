package cafepos.model.service;

import cafepos.model.domain.shoppingcart.ShoppingCart;

public interface PaymentService {
    boolean canPay(ShoppingCart shoppingCart);
    int calculatePaymentPrice(ShoppingCart shoppingCart);
    boolean processPayment(boolean confirm);
}
