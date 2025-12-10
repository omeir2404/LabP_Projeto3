# LabP_Projeto3

**A small Java project that implements a simple tabletop-style game.**

**Contents**
- **Summary:** Brief project overview and goals.
- **Build & Run:** How to compile and run the project locally.
- **Project Structure:** Short description of source files and responsibilities.
- **Usage:** How to play / run sample scenarios.
- **Extend:** How to modify or extend the game.

**Summary**
- **Project:** `LabP_Projeto3` — a compact Java implementation of a simple game using objects representing a table, bottles and game logic.
- **Purpose:** Educational / lab exercise for object-oriented design in Java. The code shows basic separation of concerns across `Bottle`, `Table`, `Game`, and a `Main` runner.

**Prerequisites**
- **Java SDK:** JDK 11 or newer installed and `java` / `javac` available on `PATH`.
- **Shell:** Example commands shown for `zsh` / POSIX shells.

**Build & Run**
- **Compile all sources:**
	- `javac *.java`
- **Run the program:**
	- `java Main`

If you'd prefer a single JAR (optional):
- Compile: `javac *.java`
- Package: `jar cfe LabP_Projeto3.jar Main *.class`
- Run JAR: `java -jar LabP_Projeto3.jar`

**Project Structure**
- `Main.java`: Program entry point; starts the game loop and coordinates I/O.
- `Game.java`: Encapsulates game rules, turn progression, and win/lose conditions.
- `Table.java`: Represents the game table state (placement of bottles, score, etc.).
- `Bottle.java`: Represents a bottle object used by the game (state, behavior).

Note: File names above correspond to the top-level `.java` files in the repository root.

**Usage / How to Play**
- Run `java Main` after compilation. The program will start and provide prompts or a runnable demonstration depending on the implementation.
- Interact using the console (keyboard input) if the game reads standard input; follow on-screen instructions.

**Extending the Project**
- Add new bottle types by extending `Bottle` and adjusting `Game` logic to use them.
- Move input/output into a separate UI class to add a GUI later.
- Add unit tests (e.g., JUnit) and create a `build.gradle` or `pom.xml` if you want reproducible builds and CI.

**Tips for Contributors**
- Keep responsibilities clear: `Game` for rules, `Table` for state, `Bottle` for items, `Main` for orchestration.
- Write small, focused commits and include a short message describing intent.

**Testing**
- There are no automated tests included. To add tests, create a `test` folder and use JUnit (add `pom.xml` or `build.gradle`).

**License & Contact**
- This repository does not include an explicit license file. Add a `LICENSE` if you want to specify reuse terms.
- For questions, open an issue or contact the repository owner.

----
If you want, I can:
- add a simple `run.sh` that compiles and runs the project, or
- add a `pom.xml` or `build.gradle` and a basic JUnit test to demonstrate CI readiness.
