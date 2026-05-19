# Upgrade Plan: pet-mgmt-microservices (20260519154558)

- **Generated**: 2026-05-19 15:45 UTC
- **HEAD Branch**: main
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 21: not available (baseline will be skipped)
- JDK 25.0.3: /home/diego/.sdkman/candidates/java/25.0.3-tem/bin (available, usable for verification with source/target 21)

**Build Tools**
- Maven 3.9.15: /home/diego/.sdkman/candidates/maven/3.9.15/bin
- Maven Wrapper: 3.9.14 (used by all modules; compatible with Java 21)

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260519154558
- Run tests before and after the upgrade: true

## Upgrade Goals

- Upgrade Java runtime target to the latest LTS: Java 21

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | ---------------- |
| Java | 21 | 21 | User requested latest LTS; already targeted |
| Spring Boot | 3.2.4 | 3.2.4 | Compatible with Java 21 |
| Maven Wrapper | 3.9.14 | 3.9.0 | Compatible with Java 21 |
| Maven | 3.9.15 | 3.9.0 | Compatible with Java 21 |
| Spring Cloud | 2023.0.1 | 2023.0.1 | Compatible with Spring Boot 3.2.4 |
| Lombok | 1.18.42 | 1.18.42 | No upgrade required |

## Derived Upgrades

- None. The project already targets Java 21 across all modules and uses compatible build tooling.

## Impact Analysis

### Dependency Changes

No dependency or plugin upgrades are required for the Java upgrade goal.

### Source Code Changes

No source code changes are required for the Java upgrade goal.

### Configuration Changes

No application or JVM configuration changes are required for the Java upgrade goal.

### CI/CD Changes

No CI/CD file updates are required at this time.

### Risks & Warnings

- The current environment does not have JDK 21 installed. The baseline step will be skipped because the project target is Java 21 but only JDK 25 is available locally.
- If the target environment later requires execution on a Java 21 runtime, ensure a Java 21 JDK is installed for runtime validation.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Confirm that the project can be built with the available toolchain and identify JDK availability gaps.
  - **Changes to Make**: None; validate JDK 25.0.3 and Maven wrapper 3.9.14.
  - **Verification**: `mvn -version` or use module `./mvnw -v` under a representative module.

- Step 2: Setup Baseline
  - **Rationale**: Baseline verification is desired, but the explicitly targeted JDK 21 is not installed locally.
  - **Changes to Make**: None.
  - **Verification**: Skipped because JDK 21 is unavailable; confirm with `java -version` that JDK 21 is not present.

- Step 3: Final Validation
  - **Rationale**: Confirm the current codebase already meets the requested Java 21 runtime goal and verify compile/test success.
  - **Changes to Make**: None.
  - **Verification**: Run `./ms-auth/mvnw clean test` or a multi-module equivalent if supported; if wrapper module-level execution is not practical, run a sample service build with Maven 3.9.15.
