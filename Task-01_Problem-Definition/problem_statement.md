# Problem Statement

## Project

Jenkins Deployment for a Customer Data Usage Alert Portal

## Problem

Telecom and internet providers collect usage events from mobile and broadband plans. When support teams must compare these events with each customer's allowance manually, customers can cross a limit before anyone notices. Records may be scattered across systems, and teams lack a single view that explains which customer is approaching a quota and why an alert was raised.

## Proposed MVP

The Customer Data Usage Alert Portal provides a small operator-facing dashboard to enter or simulate usage events, search by customer or plan, view summary indicators, open customer details, and review warning or exceeded records. A REST API exposes the same records for future integrations. The demo uses synthetic data and does not send a notification or change a customer account.

## Goals

- Make current synthetic usage records searchable from one dashboard.
- Alert operators at 80% of a plan quota and show a separate exceeded status at 100%.
- Reduce time spent finding a high-usage customer to under two minutes in a five-record demonstration.
- Return a local dashboard request within two seconds and health status within one second on the demo host.
- Require every invalid usage entry to be rejected with a visible validation error or a client error response.

## Constraints and assumptions

- The project is a student MVP, not a production telecom billing system.
- Java 17, Spring Boot, Maven, H2, Jenkins, Selenium WebDriver, Docker, and Ansible are the selected demonstration stack.
- Sample records are synthetic. Real personal, billing, or customer identifiers must not be entered.
- The local H2 database supports the demo only; production would require a managed database, backups, access control, and a retention policy.
- Email, SMS, authentication, billing-system integration, and live ingestion are outside this release.
- Jenkins, Docker Hub, GitHub review, and provisioning evidence require accounts or a Linux/WSL host controlled by the student.

## Success criteria

| Measure | Target | How to demonstrate |
|---|---:|---|
| Dashboard load | Under 2 seconds for the demo data set | Open the local dashboard |
| Alert threshold | Warning from 80% through 99.9%; exceeded at 100% and higher | Unit tests and sample records |
| Search | Find a customer by name, ID, or plan | Selenium journey and UI search |
| Health endpoint | HTTP 200 with status UP | GET /actuator/health |
| Delivery gate | Deployment stages run only after Maven and Selenium tests pass | Jenkins console and test report |

## Stakeholders

See stakeholder_list.md.
