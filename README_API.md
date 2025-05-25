# JSONPlaceholder API Automation Tests

## Overview

This project contains automated API tests for the JSONPlaceholder service (https://jsonplaceholder.typicode.com/). It utilizes Rest Assured and TestNG to perform various API operations (Create, Retrieve, Update, Delete) and validates the responses, including JSON Schema validation.

## Technologies Used

*   **Language:** Java (JDK 11 or higher)
*   **Build Tool:** Apache Maven
*   **Testing Framework:** TestNG
*   **API Testing Library:** Rest Assured
*   **JSON Schema Validation:** Rest Assured JSON Schema Validator module

## Prerequisites

*   **Java Development Kit (JDK):** Version 11 or higher installed.
*   **Apache Maven:** Installed and configured in your system's PATH.
*   **Git:** (Optional) For cloning the repository.

## Setup

1.  **Clone the Repository:**
    ```bash
    # Replace <your-repository-url> with the actual URL
    git clone <your-repository-url>
    # Replace <repository-directory> with the actual project folder name
    cd <repository-directory>
    ```

2.  **Verify JDK Configuration:**
    *   Ensure your `JAVA_HOME` environment variable points to a JDK 11+ installation.
    *   Alternatively, configure your IDE (like IntelliJ IDEA) to use JDK 11+ for the project and for Maven execution.
        *   **IntelliJ Project SDK:** `File` -> `Project Structure...` -> `Project` -> `SDK`
        *   **IntelliJ Maven Importer JDK:** `Preferences/Settings` -> `Build, Execution, Deployment` -> `Build Tools` -> `Maven` -> `JDK for importer` (Set to 'Use Project SDK' or select JDK 11+ explicitly).
        *   **IntelliJ Terminal JDK (if running `mvn` from IDE terminal):** `Preferences/Settings` -> `Tools` -> `Terminal` -> `Environment variables` (Set `JAVA_HOME=/path/to/jdk-11+`). Remember to open a *new* terminal tab after changing this setting.

## Running Tests

Tests can be executed using Maven from the command line.

1.  **Open a Terminal or Command Prompt:** Navigate to the root directory of the project.

2.  **(If Necessary) Set JAVA_HOME for the Session:** If Maven defaults to an older JDK in your terminal (verify with `mvn -v`), you might need to set `JAVA_HOME` for the current session before running the tests:
    ```bash
    # Example for macOS/Linux (adjust path if needed)
    export JAVA_HOME=/path/to/your/jdk-11+
    
    # Example for Windows Command Prompt (adjust path if needed)
    set JAVA_HOME=C:\\path\\to\\your\\jdk-11+
    
    # Example for Windows PowerShell (adjust path if needed)
    $env:JAVA_HOME = "C:\\path\\to\\your\\jdk-11+"
    ```
    *Note: Setting `JAVA_HOME` correctly in your system environment variables or IDE settings is the preferred permanent solution.* You confirmed earlier that setting it in the IntelliJ Terminal preferences didn't work automatically for new terminals, so the `export` command might be needed each time in a new terminal session within IntelliJ for your specific setup.

3.  **Run Maven Test Command:**
    ```bash
    mvn clean test
    ```
    Maven will compile the code, download dependencies (if needed), and execute the TestNG tests defined in `src/test/java`. Test results will be displayed in the console output.

## Project Structure

```
.
├── pom.xml                 # Maven Project Object Model file (dependencies, build config)
├── src
│   ├── main
│   │   └── java            # Main application code (if any)
│   └── test
│       ├── java
│       │   └── test
│       │       └── JsonPlaceholderTest.java  # TestNG test class
│       └── resources
│           ├── post-schema.json            # Schema for single post object (Create/Update)
│           ├── posts-array-schema.json     # Schema for the array of posts (Retrieve All)
│           └── empty-object-schema.json    # Schema for the empty object (Delete)
└── README.md               # This file
```

## Notes

*   The tests interact with the live JSONPlaceholder API.
*   Schema files are located in `src/test/resources` and referenced using `matchesJsonSchemaInClasspath` in the test code.

