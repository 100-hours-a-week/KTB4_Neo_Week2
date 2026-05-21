package cafepos.model.service;

import cafepos.model.domain.menu.MenuData;

public interface LogService {
    void logOrder(MenuData menu, int qty);
    void logPayment(int totalPrice);
    void logExit();
}
