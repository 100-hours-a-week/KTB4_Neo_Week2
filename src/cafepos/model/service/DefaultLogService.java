package cafepos.model.service;

import cafepos.model.domain.menu.MenuData;
import cafepos.model.infra.logging.EventLogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefaultLogService implements LogService {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final EventLogger eventLogger;

    public DefaultLogService(EventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void logOrder(MenuData menu, int qty) {
        simulateDelay();
        eventLogger.append(String.format("[%s]  ORDER  %s  %d개  [%s]", now(), menu.getName(), qty, threadName()));
    }

    @Override
    public void logPayment(int totalPrice) {
        simulateDelay();
        eventLogger.append(String.format("[%s]  PAYMENT  %d원  [%s]", now(), totalPrice, threadName()));
    }

    @Override
    public void logExit() {
        simulateDelay();
        eventLogger.append(String.format("[%s]  EXIT  [%s]", now(), threadName()));
    }

    private String now() {
        return LocalDateTime.now().format(TIME_FORMAT);
    }

    private String threadName() {
        return Thread.currentThread().getName();
    }
}
