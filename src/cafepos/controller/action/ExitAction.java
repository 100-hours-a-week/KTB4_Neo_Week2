package cafepos.controller.action;

public class ExitAction implements MainAction{
    private final ActionContext ctx;

    public ExitAction(ActionContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void action() {
        ctx.outputView().printExit();
        ctx.asyncTaskManager().execute(() -> ctx.logService().logExit());
        ctx.asyncTaskManager().shutdown();
        ctx.stop();
    }


}
