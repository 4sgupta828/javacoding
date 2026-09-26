# Glossary

Plain-language definitions. Add to this as you go — writing your own definition is one of the best ways to learn.

## Core Java
- **JDK (Java Development Kit)** — the software that lets you write *and* run Java. Includes the compiler (`javac`) and the runtime (`java`).
- **JVM (Java Virtual Machine)** — the program that actually runs your compiled Java. It's why Java runs on many devices.
- **Source code** — the `.java` text file you write.
- **Bytecode** — the `.class` file the compiler produces; what the JVM runs. (Source → `javac` → bytecode → JVM.)
- **Compile** — translate source code into bytecode. Errors caught here are *compile-time* errors.
- **Runtime** — while the program is actually running. Errors here (like `NullPointerException`) are *runtime* errors.
- **`main` method** — where a plain Java program starts running.
- **Statement** — one instruction, usually ending in `;`.
- **Variable** — a named box that holds a value.
- **Type** — what kind of value a variable holds (`int`, `double`, `boolean`, `String`, ...).
- **Method** — a named, reusable block of code; may take inputs (*parameters*) and give back a *return value*.
- **Class** — a blueprint for making objects.
- **Object** — a specific thing built from a class.
- **Field** — a variable that belongs to an object.
- **Package** — a folder/namespace for classes (`package org.firstinspires.ftc.teamcode;`).
- **Import** — a line that lets you use a class from another package.

## Words you'll meet later
- **Exception** — an error object thrown when something goes wrong at runtime.
- **NullPointerException (NPE)** — you tried to use an object that was never set up (it was `null`). The #1 FTC beginner crash.
- **Inheritance** — one class building on another (`class Dog extends Animal`).
- **Interface** — a contract: a list of methods a class promises to provide.
- **Enum** — a type with a fixed set of named values (great for robot states).
- **Annotation** — a `@Tag` that adds information to code (`@Override`, `@TeleOp`).

## FTC words
- **OpMode** — one robot program (a mode of operation). Two flavors: `LinearOpMode` (a script) and iterative `OpMode` (callbacks).
- **TeleOp** — driver-controlled mode. **Autonomous** — the robot runs on its own.
- **Telemetry** — the data your robot shows on the Driver Station (`telemetry.addData(...); telemetry.update();`). *Not* the same as logging.
- **hardwareMap** — the object you ask for hardware by name: `hardwareMap.get(DcMotor.class, "left_drive")`.
- **DcMotor / Servo** — motor and servo objects; you call methods like `setPower(...)` / `setPosition(...)`.
- **Gamepad** — the controller; you *poll* its fields every loop (`gamepad1.a`, `gamepad1.left_stick_y`).
- **ElapsedTime** — a stopwatch object for time-based actions (`timer.seconds()`).
- **Encoder** — counts motor rotation in "ticks" (an `int`); used to drive precise distances.
