package cafepos.controller.action;

import cafepos.model.domain.menu.MenuCategory;
import cafepos.model.domain.menu.MenuData;
import cafepos.model.domain.option.IceAmount;
import cafepos.model.domain.option.IceOrHot;
import cafepos.model.domain.order.OrderItem;

public class OrderAction implements MainAction{
    private final ActionContext ctx;

    public OrderAction(ActionContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void action() {
        int menuId = ctx.inputView().readMenuId();
        MenuData selectedMenu = MenuData.findById(menuId);

        if (selectedMenu == null) {
            ctx.outputView().printInvalidMenuSelect();
            return;
        }

        int qty = ctx.inputView().readQuantity();
        boolean isDrink = selectedMenu.getCategory().isDrink();

        if (isDrink) {
            for (int i = 1; i <= qty; i++) {
                OrderItem orderItem = new OrderItem(selectedMenu, 1);

                if (selectedMenu.isAvailableIce()) {
                    IceOrHot iceOrHot = ctx.inputView().readIceOrHot(i);
                    orderItem.setIceOrHot(iceOrHot);

                    if (iceOrHot == IceOrHot.ICE) {
                        IceAmount ice = ctx.inputView().readIceAmount(i);
                        orderItem.setIceAmount(ice);
                    }
                }

                ctx.cart().addItem(orderItem);
            }
        } else {
            OrderItem orderItem = new OrderItem(selectedMenu, qty);

            if (selectedMenu.getCategory() == MenuCategory.CAKE) {
                int fork = ctx.inputView().readForkCount();
                orderItem.setForkCount(fork);
            }

            ctx.cart().addItem(orderItem);
        }

        ctx.outputView().printAddCart();
        ctx.asyncTaskManager().execute(() -> ctx.logService().logOrder(selectedMenu, qty));
    }
}
