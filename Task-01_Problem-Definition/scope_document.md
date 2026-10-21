# Scope Document

## In scope

1. Create a usage record with customer ID/name, plan, billing period, usage, quota, and source.
2. Search records by customer name, customer ID, or plan.
3. Show customer count, alert count, and average quota usage for the visible records.
4. Open a detail view with the usage amount, quota, percent, source, timestamp, and status rule.
5. List warning/exceeded records and expose them through a REST endpoint.
6. Build and verify the application with Maven; run browser journeys with Selenium WebDriver.
7. Prepare Jenkins CI/CD, Docker image lifecycle, and Ansible provisioning instructions and files.

## Out of scope

- Live network or billing feed, account management, authentication, SMS/email delivery.
- Automated suspension or charging decisions.
- Production-scale availability, multi-tenancy, and sensitive personal data.
- A cloud deployment requiring paid infrastructure.

## MVP acceptance

- [x] A new valid usage event appears on the dashboard.
- [x] Search narrows the dashboard records.
- [x] A customer row opens a detail view.
- [x] Values at 80% or above appear in the alert list.
- [x] Values at or above quota show the exceeded status.
- [x] REST endpoints return JSON and application health.
- [x] Maven and Selenium tests form the pipeline quality gate.
