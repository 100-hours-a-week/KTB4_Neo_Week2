package cafepos.controller;

import cafepos.controller.action.*;

import cafepos.model.infra.logging.FileEventLogger;
import cafepos.model.service.*;
import cafepos.model.domain.menu.MenuData;
import cafepos.model.domain.store.StoreInfo;
import cafepos.model.domain.shoppingcart.ShoppingCart;
import cafepos.model.infra.receipt.ContentBuilder;
import cafepos.model.infra.receipt.FileWriter;

import cafepos.view.InputView;
import cafepos.view.OutputView;

import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PosController {
    private final Scanner sc = new Scanner(System.in);

    private final List<MenuData> menuData = new ArrayList<>();
    private final ShoppingCart curShoppingCart = new ShoppingCart();

    private final InputView inputView = new InputView(sc);
    private final OutputView outputView = new OutputView();

    private final Map<Integer, MainAction> actions;
    private final ActionContext actionContext;
    private final MainAction invalidAction;

    public PosController() {
        PaymentService paymentService = new DefaultPay();
        ReceiptService receiptService = new ReceiptService(
                new ContentBuilder(),
                new FileWriter(),
                StoreInfo.Store());
        LogService logService = new DefaultLogService(new FileEventLogger());

        this.actionContext = new ActionContext(
                menuData,
                curShoppingCart,
                inputView,
                outputView,
                paymentService,
                receiptService,
                logService
        );


        MainAction invalid = new InvalidAction(actionContext);

        this.actions = Map.of(
                1, new OrderAction(actionContext),
                2, new PayAction(actionContext),
                3, new ExitAction(actionContext)
        );

        this.invalidAction = invalid;
    }

    public static void main(String[] args) {

        PosController pos = new PosController();
        pos.initMenu();
        pos.start();

    }


    private void initMenu() {
        menuData.addAll(List.of(MenuData.values()));
    }


    private void start() {
        while(actionContext.isRunning()) {
            outputView.printMainScreen(menuData, curShoppingCart);
            outputView.printMainActions();

            int input = inputView.readMainMenu();
            actions.getOrDefault(input, invalidAction).action();
        }
    }

}
