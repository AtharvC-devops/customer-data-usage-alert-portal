# Software Requirements Summary

## Purpose

Give customer operations and support staff one view of synthetic customer data usage events and surface records nearing or exceeding a configured plan quota.

## Functional requirements

1. The dashboard shall display usage records ordered by newest recording time.
2. An operator shall be able to create a usage record through the web form or REST API.
3. The application shall validate customer fields, non-negative usage, and a positive quota.
4. Search shall match customer ID, customer name, or plan without case sensitivity.
5. The dashboard shall show distinct customer count, alert count, and average usage percentage.
6. A warning shall be raised from 80% up to but not including 100%; an exceeded alert shall be raised at 100% or more.
7. An operator shall open a detail page showing plan, billing period, source, timestamp, usage, quota, and status rule.
8. JSON endpoints shall provide usage records, a single record, alerts, summary, and a simulation entry point.
9. The application shall expose an HTTP health endpoint for deployment validation.

## Non-functional requirements

- The local app should be easy to start with Java 17 and Maven.
- The demo dashboard should load within two seconds for the small synthetic data set.
- Data shall be validated on the server and rendered using Thymeleaf's escaped text output.
- Runtime configuration shall use environment variables; no credentials are committed.
- The Docker image shall use a non-root runtime account and expose port 8085.
- Jenkins shall run the tests before image publication or deployment.
- The demo contains no authentication and must not store real personal or billing data.

## Actors

Customer operations agent, customer support representative, network analyst, service administrator, and DevOps engineer.

## Constraints

Embedded H2 is selected to keep the MVP self-contained. Jenkins, Docker Hub, and Ansible execution require services and credentials that belong to the student. The current source does not claim those external stages ran.

