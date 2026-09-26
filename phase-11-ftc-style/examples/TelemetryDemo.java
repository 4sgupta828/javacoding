// Phase 11 — a tiny telemetry-style helper (shaped like FTC's addData / update),
// plus the reused Phase-6 Log helper, to show that LOGGING and TELEMETRY are different jobs.
// Run:   java TelemetryDemo.java

import java.util.ArrayList;
import java.util.List;

public class TelemetryDemo {

    // Shaped like FTC telemetry: you QUEUE labeled lines, then update() flushes them.
    // Telemetry is for the DRIVER, right now, on the Driver Station screen.
    static class FakeTelemetry {
        private final List<String> lines = new ArrayList<>();

        void addData(String label, Object value) {   // queue one line
            lines.add(label + ": " + value);
        }

        void update() {                              // actually "send" the queued lines
            System.out.println("---- Driver Station ----");
            for (String line : lines) System.out.println("  " + line);
            lines.clear();                           // telemetry clears itself after each update
        }
    }

    // The Phase-6 Log helper: level + message over System.out.
    // Logging is a permanent record for YOU (the programmer), for debugging later.
    static void log(String level, String msg) {
        System.out.println("[" + level + "] " + msg);
    }

    public static void main(String[] args) {
        FakeTelemetry telemetry = new FakeTelemetry();

        log("INFO", "OpMode starting");            // -> the log (for debugging)
        telemetry.addData("Status", "Running");    // -> queued for the driver
        telemetry.addData("Battery", 12.6);
        telemetry.update();                         // nothing shows to the driver until THIS call

        log("WARN", "Battery getting low");         // driver may never see this; you will, in the log

        telemetry.addData("Status", "Stopping");
        telemetry.update();
    }
}
