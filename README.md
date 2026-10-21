# Customer Data Usage Alert Portal

**Student:** Atharv Chikane · **Roll No:** 23102C0016 · **Class:** BE CMPN-C  
**Course:** DevOps · **Academic Year:** 2026–27 · **Topic:** Jenkins Deployment for a Customer Data Usage Alert Portal

A small customer operations portal that records data usage events, highlights customers nearing or exceeding their plan quota, and exposes the same information through a REST API. The repository demonstrates an end-to-end DevOps workflow with Maven, Selenium WebDriver, Jenkins, Docker, and Ansible.

## Features

- Record usage events with customer, plan, period, source, used data, and quota.
- Search records by customer name, customer ID, or plan.
- Show customer count, alert count, and average quota usage.
- Open a customer detail view with usage percentage and status explanation.
- Flag usage at 80% or above as a warning and usage at or above quota as exceeded.
- Seed synthetic sample data so the dashboard is useful on first start.
- Expose JSON endpoints for usage records, alerts, summary, and alert simulation.
- Persist local/container data to an H2 database file.

## Technology

| Area | Choice |
|---|---|
| Application | Java 17, Spring Boot 3.3.5, Thymeleaf |
| Build | Maven |
| Database | H2 embedded database |
| Web server | Embedded Tomcat |
| CI/CD | Jenkins Pipeline |
| Browser checks | Selenium WebDriver with headless HtmlUnit template/form smoke test |
| Container | Docker |
| Configuration management | Ansible |

## Run locally

Prerequisites: JDK 17 or newer and Maven 3.8 or newer.

From the project root, run:

    mvn spring-boot:run

Open http://localhost:8085. The first launch creates synthetic usage records and a local data folder. To package and run the jar:

    mvn clean verify
    java -jar target/customer-usage-alert-portal.jar

The application port can be changed with SERVER_PORT. The database URL can be changed with DB_URL. The default local database is file-based at data/usage-alert.

## API

| Method | Path | Purpose |
|---|---|---|
| GET | /api/usages | List records; optional q search query |
| GET | /api/usages/{id} | Read one record |
| POST | /api/usages | Add a validated usage record |
| GET | /api/alerts | List warning and exceeded records |
| GET | /api/summary | Read dashboard indicators |
| POST | /api/simulate | Add a synthetic usage event |
| GET | /actuator/health | Container/Jenkins health check |

Example request body for POST /api/usages:

    {
      "customerId": "CUS-1005",
      "customerName": "Nisha Rao",
      "planName": "Home Plus",
      "billingPeriod": "2026-10",
      "usedGb": 87.5,
      "quotaGb": 100,
      "source": "Manual entry"
    }

## DevOps workflow

1. Create a feature branch and commit one change at a time.
2. Jenkins checks out the repository, runs Maven verification, publishes JUnit reports, and archives the jar.
3. With the pipeline parameters enabled, Jenkins builds a versioned Docker image, optionally pushes it to Docker Hub, and can replace a local container after tests pass.
4. Ansible configures a Linux/WSL target, sets environment values, starts Docker, and deploys the selected image.
5. The health endpoint and rollback steps are documented under the task folders.

See CHECKPOINTS.md for the task-by-task status and STUDENT_ACTIONS.md for the account and screenshot steps that still require you.

## Repository map

- src/ — application, templates, static styles, and tests
- Task-01_Problem-Definition/ through Task-15_Final-Release-Docs/ — coursework artifacts and runbooks
- Jenkinsfile — Jenkins CI pipeline with optional Docker publish/deploy stages
- Dockerfile and docker-compose.yml — local container workflow
- Task-13_Config-Management/ — Ansible inventory and playbooks
- screenshots/ — save real screenshots here after running Jenkins, Docker, Ansible, and the app

## Scope and limitations

This is a coursework MVP with synthetic records. It has no authentication, billing integration, live network feed, or customer notification delivery. Do not load personal or real customer data into the demo. Threshold-based alerts are visible in the portal and API; sending email/SMS is a future extension.
