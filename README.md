# AI-Generated Spring Boot Application (Claude Sonnet 4.5)

This repository contains a Spring Boot application generated incrementally using Claude Sonnet 4.5 for a university assignment focused on evaluating generative AI in software development.

The project uses **Java 17** and **Spring Boot 3**, and represents a simple planetary system consisting of planets and moons. The application was developed entirely through AI prompts, with each prompt recorded as a separate commit to demonstrate the development process.

## Features
- Spring Boot 3 (Java 17)
- Layered architecture (entities, repositories, services, controllers)
- JPA with H2 in-memory database
- REST API for planets and moons
- DTOs with validation
- Centralised exception handling
- Attempted Spring Security integration with users and roles
- Sample data initialisation

## Running the application
1. Open the project in IntelliJ IDEA.
2. Ensure Java 17 is configured.
3. Run the main Spring Boot application class.

The application runs successfully up to the security implementation stage.

## Known issues
At the final prompt, the AI-generated security configuration introduces a circular dependency related to password encoding, causing the application to fail at startup. This issue has been left unresolved intentionally to preserve the integrity of the AI-generated output.

## Notes
This repository is used to critically evaluate the strengths and limitations of generative AI when building a Spring Boot application. The differences between this implementation and other AI-generated versions are analysed in the written report.
