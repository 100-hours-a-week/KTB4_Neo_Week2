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

    @Override
    public void logOrder(MenuData menu, int qty) {
        eventLogger.append(String.format("[%s]  ORDER  %s  %d개", now(), menu.getName(), qty));
    }

    @Override
    public void logPayment(int totalPrice) {
        eventLogger.append(String.format("[%s]  PAYMENT  %d원", now(), totalPrice));
    }

    @Override
    public void logExit() {
        eventLogger.append(String.format("[%s]  EXIT", now()));
    }

    private String now() {
        return LocalDateTime.now().format(TIME_FORMAT);
    }
}
