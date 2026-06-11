# java-features

Multi-module Spring Boot project where each module is built with its own JDK:

- `java-8`  → Java 8  → Spring Boot 2.7.9
- `java-11` → Java 11 → Spring Boot 2.7.9
- `java-17` → Java 17 → Spring Boot 4.0.5
- `java-21` → Java 21 → Spring Boot 4.0.5
- `java-25` → Java 25 → Spring Boot 4.0.5

## Why these Spring Boot versions?

- Spring Boot 2.7.9 requires Java 8 and supports up to Java 19.
- Spring Boot 4.0.5 requires Java 17+ and is compatible up to Java 26.

## Build prerequisites

Install all required JDKs locally and configure Maven Toolchains.

### Example `~/.m2/toolchains.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<toolchains xmlns="http://maven.apache.org/TOOLCHAINS/1.1.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/TOOLCHAINS/1.1.0 https://maven.apache.org/xsd/toolchains-1.1.0.xsd">
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>8</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk8</jdkHome>
        </configuration>
    </toolchain>

    <toolchain>
        <type>jdk</type>
        <provides>
            <version>11</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk11</jdkHome>
        </configuration>
    </toolchain>

    <toolchain>
        <type>jdk</type>
        <provides>
            <version>17</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk17</jdkHome>
        </configuration>
    </toolchain>

    <toolchain>
        <type>jdk</type>
        <provides>
            <version>21</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk21</jdkHome>
        </configuration>
    </toolchain>

    <toolchain>
        <type>jdk</type>
        <provides>
            <version>25</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk25</jdkHome>
        </configuration>
    </toolchain>
</toolchains>
```

## Build all modules

```bash
mvn clean install
```

## Run a module

Examples:

```bash
mvn -pl java-17 spring-boot:run
mvn -pl java-21 spring-boot:run
```

## Module layout

Each module contains:

- its own `pom.xml`
- a minimal `@SpringBootApplication`
- a simple REST controller
- a smoke test
