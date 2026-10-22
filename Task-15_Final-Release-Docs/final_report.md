# Final Technical Report — Customer Data Usage Alert Portal

**Student:** Atharv Chikane · **Roll No:** 23102C0016 · **Class:** BE CMPN-C

## Introduction

The project is a small customer operations portal that brings data-usage events, quota summaries, and threshold alerts into one dashboard. It is designed as a course MVP and uses synthetic records.

## Problem and scope

Operators need a quick way to find customers approaching their plan limit. The MVP records usage, searches by customer or plan, calculates summary indicators, opens a status detail page, and lists warning/exceeded records.

## Architecture and stack

The Spring Boot application serves a Thymeleaf interface and JSON API. Spring Data JPA persists UsageRecord data to H2. Jenkins runs Maven and Selenium checks, can build/push a Docker image, and can replace a container. Ansible prepares a Linux host and deploys a selected image.

## Implementation

- Usage status is derived from used GB and quota: warning begins at ALERT_THRESHOLD_PERCENT (80% default), and usage at 100% or above is exceeded.
- Server-side validation rejects invalid identifiers, usage, and quota.
- Dashboard search covers customer ID, name, and plan.
- Selenium WebDriver journeys cover the dashboard/search and add/detail flow.
- Docker uses a multi-stage Java build and non-root runtime user.
- Ansible declares package, user, directory, service, and container state.

## DevOps flow

Git branch → Jenkins checkout → Maven verification → JUnit publication and jar archive → optional image build/push → optional local deployment → Ansible provisioning/validation.

## Evidence

Add actual screenshots and run results to screenshots/ after using your GitHub, Jenkins, Docker Hub, and Linux/WSL environment. This report does not claim those external runs happened.

## Limitations

No real data source, authentication, notification sender, production DB, or high-availability configuration is included. The student still needs to execute account-based steps and capture evidence for the checkpoints marked pending.

## Future enhancements

Add secure operator login, a real ingestion adapter, notification delivery, PostgreSQL, audit history, pagination, and a staging environment.

