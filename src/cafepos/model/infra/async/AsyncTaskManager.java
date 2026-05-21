package cafepos.model.infra.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncTaskManager {
    private final ExecutorService executorService;

    public AsyncTaskManager(int poolSize) {
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void execute(Runnable task) {
        executorService.execute(task);
    }

    public void shutdown() {
        executorService.shutdown();

        try {
            if(!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if(!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("Thread Pool 종료되지 않음.");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }
}
