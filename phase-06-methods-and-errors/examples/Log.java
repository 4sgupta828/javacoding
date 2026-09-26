// Phase 6 — our shared, student-built Log class.
// It's a tiny wrapper over System.out that prints a LEVEL and a MESSAGE.
// This is the same shape as FTC telemetry: you give it a label + value,
// and something prints it. We'll reuse this class in later phases.
//
// Run:   java Log.java

public class Log {

    // The core method: print "[LEVEL] message". Everything else calls this.
    public static void log(String level, String message) {
        System.out.println("[" + level + "] " + message);
    }

    // Three convenience methods — they just call log() with a fixed level.
    // (Same method name would be OVERLOADING; here we use different names
    //  so the level is obvious at the call site.)
    public static void info(String message) {
        log("INFO", message);
    }

    public static void warn(String message) {
        log("WARN", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    // A demo so you can run this file directly.
    public static void main(String[] args) {
        Log.info("Robot initialized");
        Log.warn("Battery low: 30%");
        Log.error("Motor 'left_drive' not found");
    }
}
