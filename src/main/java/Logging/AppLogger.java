package Logging;

import java.time.LocalDateTime;

public class AppLogger {
    private AppLogger() {
    }

    public static void info(String message) {
        System.err.println("[INFO " + LocalDateTime.now() + "] " + message);
    }

    public static void error(String message) {
        System.err.println("[ERROR " + LocalDateTime.now() + "] " + message);
    }
}
