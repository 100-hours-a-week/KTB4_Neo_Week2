package cafepos.model.service;

import cafepos.model.domain.shoppingcart.ShoppingCart;

public class DefaultPay implements PaymentService {
    @Override
    public boolean canPay(ShoppingCart shoppingCart) {
        return !shoppingCart.isEmpty();
    }

    @Override
    public int calculatePaymentPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getTotalPrice();
    }

    @Override
    public boolean processPayment(boolean confirm) {
        return confirm;
    }
}
