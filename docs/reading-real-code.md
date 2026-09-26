# Reading Real FTC Code (without a robot)

One of your two big goals is to **read** real FTC code and understand it. You can practice this from Phase 4 onward — you don't need a robot or Android Studio, just a web browser.

## Where real OpModes live

The official FTC SDK ships example OpModes on GitHub:

- **FtcRobotController** repo → `FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples/`

Search GitHub for `RobotAutoDriveByGyro_Linear` or `BasicOpMode_Linear` to find canonical examples. These are the same files real teams start from.

## How to read code you don't fully understand yet

You will not understand every line at first. That's fine — reading code is a skill, and here's the method:

1. **Find the shape, not the details.** Every OpMode is roughly: *set up hardware → wait for start → loop: read inputs, decide, set outputs.* Find those parts first.
2. **Ignore what you can't read yet, on purpose.** Highlight the lines you *do* recognize. Each phase, more lines light up.
3. **Predict, then check.** Cover the output/behavior and guess what a block does before reading on.
4. **Name the pieces.** "That `@TeleOp` is an annotation (Phase 10). That `extends LinearOpMode` is inheritance (Phase 9). That `hardwareMap.get(...)` returns an object (Phase 8)."

## The skeleton you're working toward

By Phase 11 you'll read this line by line and know every piece:

```java
@TeleOp                                    // annotation — tells the app this is a driver-controlled OpMode (Phase 10)
public class MyOp extends LinearOpMode {   // inheritance — MyOp IS an OpMode (Phase 9)
    private DcMotor leftDrive;             // a field: an object reference (Phase 8), private (access modifier, Phase 8)

    @Override                              // annotation — "I'm replacing the parent's method" (Phases 9–10)
    public void runOpMode() {              // the one method you must write for a LinearOpMode
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");  // look up hardware by name (Phase 8)
        waitForStart();                    // block until the driver hits START
        while (opModeIsActive()) {         // the control loop (Phase 5)
            leftDrive.setPower(-gamepad1.left_stick_y);  // read gamepad, set motor (Phase 3 polling model)
            telemetry.addData("pos", leftDrive.getCurrentPosition());  // queue data
            telemetry.update();            // actually send it to the Driver Station
        }
    }
}
```

Each phase's **"Read a real OpMode"** section points you at a snippet that uses just the pieces you know so far.

## Two ways to run code on a *real* robot (later, optional)
- **OnBot Java** — a browser-based editor built into the Robot Controller phone/hub. No Android Studio needed. Great for a team member who wants to contribute now.
- **Android Studio + Gradle** — the full toolchain most teams use for bigger projects. Heavier; we cover it only conceptually in Phase 11.

## A caveat about Java versions
You're learning on Java 21. FTC's toolchain targets an **older** Java language level, so a few *modern* conveniences you might see elsewhere (records, text blocks, sealed classes, `switch` *expressions* with `->` returning a value) may **not appear in — or even compile in —** FTC code. The core you learn here (classes, methods, loops, `var`, the classic `switch` statement) is exactly what FTC uses. Phase 11 covers this.
