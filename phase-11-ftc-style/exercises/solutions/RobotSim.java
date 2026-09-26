// Phase 11 CAPSTONE — worked solution.
//
// A simulated robot control program in PLAIN Java. There are NO real FTC packages here:
// we build tiny FAKES for Gamepad, DcMotor, and Telemetry, so the whole thing runs with:
//     java RobotSim.java
//
// It contains:
//   - a fake DcMotor that prints whenever its power changes
//   - a fake Gamepad whose inputs are scripted (no controller needed)
//   - a fake Telemetry (addData / update), like FTC
//   - an AUTONOMOUS OpMode built as a state machine (enum + switch + a timer)
//   - a TELEOP OpMode that polls the gamepad every loop (Phase 3 model)

public class RobotSim {
    public static void main(String[] args) {
        // ---- AUTONOMOUS: run the state machine until it says it's done ----
        System.out.println("===== AUTONOMOUS =====");
        AutoOpMode auto = new AutoOpMode();
        auto.init();
        while (!auto.isDone()) {
            auto.loop();
        }

        // ---- TELEOP: read a scripted gamepad for a few loops ----
        System.out.println("\n===== TELEOP =====");
        TeleOpMode teleop = new TeleOpMode();
        teleop.init();
        for (int i = 0; i < teleop.scriptLength(); i++) {
            teleop.loop();
        }
    }

    // ===================== FAKE FTC HARDWARE =====================

    // Stands in for FTC's DcMotor. Real one has setPower / getCurrentPosition.
    static class FakeDcMotor {
        private final String name;
        private double power = 0;
        FakeDcMotor(String name) { this.name = name; }
        void setPower(double p) {
            if (p != power) {                                    // only announce real changes
                System.out.println("    [motor " + name + "] power -> " + p);
            }
            power = p;
        }
        double getPower() { return power; }
    }

    // Stands in for FTC's Gamepad. Inputs are SCRIPTED: one "frame" per loop.
    static class FakeGamepad {
        private final double[] leftStickY;   // -1..1; FTC convention: pushing UP is NEGATIVE
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

    // Stands in for FTC's telemetry. Here update() just prints; on a robot it flushes to the
    // Driver Station. (Telemetry is for the driver — logging, for you, is a different job.)
    static class FakeTelemetry {
        void addData(String label, Object value) { System.out.println("    " + label + ": " + value); }
        void update() { /* on a real robot: send the queued lines to the Driver Station */ }
    }

    // ===================== AUTONOMOUS OPMODE (state machine) =====================
    static class AutoOpMode {
        enum State { DRIVE_FORWARD, TURN, STOP, DONE }   // the named steps (Phase 10)

        FakeDcMotor left      = new FakeDcMotor("left");
        FakeDcMotor right     = new FakeDcMotor("right");
        FakeTelemetry telemetry = new FakeTelemetry();

        State state       = State.DRIVE_FORWARD;
        double time       = 0;      // simulated seconds in the current state
        final double dt   = 0.5;    // each loop = 0.5 simulated seconds

        void init() {
            System.out.println("[init] auto ready, state = " + state);
        }

        boolean isDone() { return state == State.DONE; }

        void loop() {
            // ACT: set powers for the current state
            switch (state) {
                case DRIVE_FORWARD: left.setPower(1.0);  right.setPower(1.0);  break;
                case TURN:          left.setPower(1.0);  right.setPower(-1.0); break;
                case STOP:          left.setPower(0.0);  right.setPower(0.0);  break;
                case DONE:          break;
            }

            // REPORT
            telemetry.addData("state", state);
            telemetry.addData("time", time);
            telemetry.update();

            // DECIDE: advance the clock, change state when this step's time is up
            time += dt;
            switch (state) {
                case DRIVE_FORWARD: if (time >= 2.0) { state = State.TURN; time = 0; } break;
                case TURN:          if (time >= 1.0) { state = State.STOP; time = 0; } break;
                case STOP:          state = State.DONE; break;
                case DONE:          break;
            }
        }
    }

    // ===================== TELEOP OPMODE (poll the gamepad) =====================
    static class TeleOpMode {
        FakeDcMotor left      = new FakeDcMotor("left");
        FakeDcMotor right     = new FakeDcMotor("right");
        FakeTelemetry telemetry = new FakeTelemetry();
        boolean clawOpen = false;

        // scripted driver input, one value per loop (4 loops):
        FakeGamepad gamepad1 = new FakeGamepad(
            new double[] { -1.0, -0.5,  0.0,  0.0 },      // stick: full fwd, half, released, released
            new boolean[]{ false, false, true, false }    // tap A on loop 3
        );

        int scriptLength() { return 4; }

        void init() {
            System.out.println("[init] teleop ready");
        }

        void loop() {
            // READ inputs (polling — Phase 3)
            double raw = -gamepad1.leftStickY();          // FTC idiom: push up (negative) = forward
            double drive = Math.abs(raw) < 0.05 ? 0.0 : raw;  // deadzone (Phase 4)
            boolean pressA = gamepad1.a();

            // DECIDE + ACT
            left.setPower(drive);
            right.setPower(drive);
            if (pressA) {
                clawOpen = !clawOpen;                     // toggle the claw
                System.out.println("    [claw] " + (clawOpen ? "OPEN" : "CLOSED"));
            }

            // REPORT
            telemetry.addData("drive", drive);
            telemetry.addData("clawOpen", clawOpen);
            telemetry.update();

            gamepad1.nextFrame();                         // advance the scripted input
        }
    }
}
