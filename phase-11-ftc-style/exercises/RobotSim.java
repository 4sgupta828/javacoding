// Phase 11 CAPSTONE — SIMULATED robot control program (starter).
//
// The fake hardware is written for you. Your job is the ROBOT LOGIC:
// fill in the autonomous state machine and the teleop loop.
//
// Run:   java RobotSim.java
// Check against exercises/solutions/RobotSim.java when done.

public class RobotSim {
    public static void main(String[] args) {
        System.out.println("===== AUTONOMOUS =====");
        AutoOpMode auto = new AutoOpMode();
        auto.init();
        while (!auto.isDone()) {
            auto.loop();
        }

        System.out.println("\n===== TELEOP =====");
        TeleOpMode teleop = new TeleOpMode();
        teleop.init();
        for (int i = 0; i < teleop.scriptLength(); i++) {
            teleop.loop();
        }
    }

    // ===================== FAKE FTC HARDWARE (done for you) =====================

    static class FakeDcMotor {
        private final String name;
        private double power = 0;
        FakeDcMotor(String name) { this.name = name; }
        void setPower(double p) {
            if (p != power) System.out.println("    [motor " + name + "] power -> " + p);
            power = p;
        }
        double getPower() { return power; }
    }

    static class FakeGamepad {
        private final double[] leftStickY;   // FTC convention: pushing UP is NEGATIVE
        private final boolean[] aButton;
        private int frame = 0;
        FakeGamepad(double[] leftStickY, boolean[] aButton) {
            this.leftStickY = leftStickY;
            this.aButton = aButton;
        }
        double leftStickY() { return leftStickY[frame]; }
        boolean a()         { return aButton[frame]; }
        void nextFrame()    { frame++; }
    }

    static class FakeTelemetry {
        void addData(String label, Object value) { System.out.println("    " + label + ": " + value); }
        void update() { }
    }

    // ===================== AUTONOMOUS OPMODE =====================
    static class AutoOpMode {
        // TODO 1: define an enum State with the steps: DRIVE_FORWARD, TURN, STOP, DONE

        FakeDcMotor left        = new FakeDcMotor("left");
        FakeDcMotor right       = new FakeDcMotor("right");
        FakeTelemetry telemetry = new FakeTelemetry();

        // TODO 2: a State field starting at DRIVE_FORWARD, a double time = 0, a final double dt = 0.5

        void init() {
            System.out.println("[init] auto ready");
        }

        // TODO 3: isDone() should return true when the state is DONE
        boolean isDone() {
            return true;   // <-- replace this so the loop actually runs
        }

        void loop() {
            // TODO 4: ACT — switch on the state to set left/right motor powers:
            //   DRIVE_FORWARD: both +1.0    TURN: left +1.0, right -1.0    STOP: both 0.0

            // TODO 5: REPORT — telemetry.addData("state", ...) and ("time", ...); then update()

            // TODO 6: DECIDE — time += dt; then switch on the state to advance:
            //   after 2.0s DRIVE_FORWARD -> TURN (reset time)
            //   after 1.0s TURN -> STOP (reset time)
            //   STOP -> DONE
        }
    }

    // ===================== TELEOP OPMODE =====================
    static class TeleOpMode {
        FakeDcMotor left        = new FakeDcMotor("left");
        FakeDcMotor right       = new FakeDcMotor("right");
        FakeTelemetry telemetry = new FakeTelemetry();
        boolean clawOpen = false;

        FakeGamepad gamepad1 = new FakeGamepad(
            new double[] { -1.0, -0.5,  0.0,  0.0 },
            new boolean[]{ false, false, true, false }
        );

        int scriptLength() { return 4; }

        void init() {
            System.out.println("[init] teleop ready");
        }

        void loop() {
            // TODO 7: READ — double drive = -gamepad1.leftStickY();  (flip the Y — Phase 3)
            //         boolean pressA = gamepad1.a();

            // TODO 8: ACT — set both motors to drive; if pressA, flip clawOpen and print it

            // TODO 9: REPORT — telemetry.addData("drive", ...) and ("clawOpen", ...); update()

            gamepad1.nextFrame();   // leave this last — it advances the scripted input
        }
    }
}
