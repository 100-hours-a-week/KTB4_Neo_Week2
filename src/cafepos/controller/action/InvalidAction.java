package cafepos.controller.action;

public class InvalidAction implements MainAction {
    private final ActionContext ctx;

    public InvalidAction(ActionContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void action() {
        ctx.outputView().printInvalidChoice();
    }
}
