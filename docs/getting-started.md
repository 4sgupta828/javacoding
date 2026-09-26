# Getting Started on macOS

You need to install Java once. Budget ~20 minutes. Do this before Phase 1.

## 1. Install the JDK (the thing that runs Java)

macOS does **not** come with Java. If you type `java` on a fresh Mac you'll get a popup like *"No Java runtime present."* That's expected — install it:

**Recommended: Homebrew** (a package manager for Mac).

If you don't have Homebrew, install it from [brew.sh](https://brew.sh), then:

```bash
brew install --cask temurin@21
```

This installs **Eclipse Temurin 21**, a free, standard build of Java. Homebrew automatically picks the right version for your chip (Apple Silicon `aarch64` vs Intel `x64`).

> No Homebrew? Download the `.pkg` from [adoptium.net](https://adoptium.net) — but **pick the right chip**: Apple Silicon (M1/M2/M3/M4) → `aarch64`; older Intel Macs → `x64`. Homebrew avoids this guesswork.

## 2. Check it worked

Open **Terminal** and run:

```bash
java -version
```

You should see something like:

```
openjdk version "21.0.x" ...
```

If you instead get the "No Java runtime" popup, the install didn't register on your PATH. Fix it by setting `JAVA_HOME` in your shell config (macOS uses **zsh**, so the file is `~/.zshrc`):

```bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v21)' >> ~/.zshrc
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

Then run `java -version` again.

## 3. Install an editor

**VS Code** + the **Extension Pack for Java** (search it in the Extensions panel) is a good free choice.

- Heads-up: the Java extension sometimes can't find your JDK on first launch. If it complains, set `JAVA_HOME` (step 2) and restart VS Code, or set `java.jdt.ls.java.home` in VS Code settings to the path from `/usr/libexec/java_home -v21`.
- Alternative: **IntelliJ IDEA Community Edition** (free). It's smoother for pure Java and is a cousin of Android Studio (what FTC uses), so it pays off later.

## 4. Two macOS traps that waste beginners' first hour

1. **Finder hides file extensions.** Turn them on: Finder → Settings → Advanced → *"Show all filename extensions."* Otherwise you'll create `Hello.java.txt` without realizing and nothing will run.
2. **Don't use TextEdit.** Its default saves *rich text*, not plain text — Java can't read that. Use VS Code (or IntelliJ). Always.

If a downloaded file is blocked ("developer cannot be verified"), allow it in System Settings → Privacy & Security, or run `xattr -d com.apple.quarantine <file>`.

## 5. How you'll run programs

Two ways — you'll learn both in Phase 1:

**The simple way (what we use most):** run a source file directly, no separate compile step.
```bash
java Hello.java
```

**The explicit way (shown once, so you understand "compiling"):**
```bash
javac Hello.java   # creates Hello.class (bytecode)
java Hello         # runs the class
```

**Exploring quickly?** Use the REPL — type Java one line at a time and see results instantly:
```bash
jshell
```
```
jshell> 3 + 4 * 2
$1 ==> 11
jshell> /exit
```

## You're ready
Head to [`phase-01-getting-started/README.md`](../phase-01-getting-started/README.md).
