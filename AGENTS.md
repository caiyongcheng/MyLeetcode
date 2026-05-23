# Repository Guidelines

## Project Structure & Module Organization

This repository is a Java 8 Maven project for LeetCode solutions and helper utilities.

- `src/main/java/letcode/`: solution classes, grouped by source and difficulty, such as `normal/easy`, `normal/medium`, `normal/difficult`, `offer`, `lcp`, and `lcr`.
- `src/main/java/datastructure/`: reusable data structures and supporting exceptions.
- `src/main/java/letcode/utils/`: shared test utilities, codecs, runner code, and node models.
- `src/main/resources/`: text-based test cases, commonly named `TestCase<ClassName>.txt`, for example `TestCase_632.txt`.
- `src/test/java/`: JUnit 5 tests for shared utilities.
- `scripts/`: maintenance scripts, including test-case migration tooling.
- `idea-testutil-runner-plugin/`: standalone IntelliJ IDEA plugin project built with Gradle/Kotlin DSL.

Generated output lives in `target/` and plugin `build/` directories; do not edit or commit generated artifacts unless explicitly required.

## Build, Test, and Development Commands

- `mvn test`: compile the Maven project and run JUnit 5 tests.
- `mvn -DskipTests package`: build the main project without running tests.
- `mvn test -Dtest=TestCaseInputUtilsTest`: run one test class.
- `powershell -File scripts/migrate-main-testcases.ps1`: migrate inline `main` test-case comments into resource files.
- `cd idea-testutil-runner-plugin; .\build.ps1`: build the IntelliJ plugin zip.

Run commands from the repository root unless the command changes directory explicitly.

## Coding Style & Naming Conventions

Use Java 8-compatible code and follow the existing compact solution style. Use 4-space indentation. Keep solution classes close to the current naming pattern: LeetCode numbered classes usually use `_123.java`; unresolved or generated variants may use `N_123.java`. Prefer package placement by problem family and difficulty.

Keep changes focused. Add short Chinese comments only for non-obvious algorithm intent, important edge cases, or runner/test utility behavior.

## Testing Guidelines

Tests use JUnit 5 via Maven Surefire. Place unit tests under `src/test/java` and name them `*Test.java`. Shared utility changes should include or update focused tests. For solution classes, add or update matching resource cases in `src/main/resources/TestCase<ClassName>.txt` when using `TestUtil`/`TestUtilRunner`.

Before submitting changes, run `mvn test`. For plugin-only changes, also run the plugin build script.

## Commit & Pull Request Guidelines

Recent history uses short imperative messages, sometimes with a Conventional Commit prefix, for example `refactor: optimize leetcode 33 search` or `Add IDEA TestUtil runner plugin`. Prefer concise, action-oriented commit subjects.

Pull requests should describe the changed problem or utility, list verification commands, and note any migrated test-case files or plugin build artifacts. Link related issues when available.

## Agent-Specific Instructions

Default to Chinese for repository task responses. Preserve existing project style, avoid broad refactors, and never overwrite unrelated local changes.
