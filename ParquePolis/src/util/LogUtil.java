package util;

public class LogUtil {

    private LogUtil() {
        // Private constructor to hide the implicit public one
    }

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",  "%1$t" +
                "FT%1$tT [%4$s] %5$s%n");
    }
    static final System.Logger CONSOLE = System.getLogger("Parque Polis");

    public static void info(String msg) {
        CONSOLE.log(System.Logger.Level.INFO, msg);
    }

    public static void info(String format, Object... args      ) {
        CONSOLE.log(System.Logger.Level.INFO, format, args);
    }

    public static void warning(String msg) {
        CONSOLE.log(System.Logger.Level.WARNING, msg);
    }
    public static void warning(String format, Object... args) {
        CONSOLE.log(System.Logger.Level.WARNING, format, args);
    }

}
