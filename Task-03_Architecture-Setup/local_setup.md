# Local Development Setup

## Prerequisites

- JDK 17 or newer
- Maven 3.8 or newer
- Git
- Optional: Docker Desktop for the container tasks

## Start and check

1. Open a terminal in the repository root.
2. Run mvn clean verify to compile and execute the unit and Selenium browser-journey tests.
3. Run mvn spring-boot:run.
4. Open http://localhost:8085.
5. Open http://localhost:8085/actuator/health and check for status UP.
6. Search for CUS-1003, add a synthetic record at 85/100 GB, and open its detail page.

The default database is written under data/. The test profile uses a disposable in-memory database. Change SERVER_PORT or DB_URL to use another port or database URL.

