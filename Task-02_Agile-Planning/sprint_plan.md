# Sprint Plan

## Sprint 1 — Scope, design, and application baseline

Tasks: define problem/users, freeze MVP, select stack, create requirements and diagrams, initialize repository, build dashboard shell and synthetic data.

Completion: user can view sample usage and open the dashboard locally.

## Sprint 2 — MVP and quality gate

Tasks: event entry, search, summaries, detail view, alert thresholds, REST API, JUnit/Selenium tests, Jenkins pipeline.

Completion: Maven verification passes and failed tests prevent later stages.

## Sprint 3 — Container, provisioning, and evidence

Tasks: Docker image, optional registry publish/deployment, Ansible provisioning, health check, idempotency, rollback, final report.

Completion: a fresh target can run the selected image; repeat provisioning is idempotent and the previous tag can be restored.

## 15-item Kanban board

| # | Work item | Column |
|---:|---|---|
| 1 | Freeze problem scope | Done |
| 2 | Write stakeholders and success criteria | Done |
| 3 | Prepare user stories and backlog | Done |
| 4 | Design diagrams and model | Done |
| 5 | Implement usage entity and alert rules | Done |
| 6 | Build dashboard, search, entry, and details | Done |
| 7 | Add API and health check | Done |
| 8 | Write JUnit and Selenium tests | Done |
| 9 | Add Jenkinsfile and test reporting | Ready to run |
| 10 | Configure Jenkins job and credentials | Student action |
| 11 | Build and inspect Docker image | Ready to run |
| 12 | Push image and deploy through Jenkins | Student credentials |
| 13 | Provision Linux target using Ansible | Student action |
| 14 | Prove idempotency, health, and rollback | Student action |
| 15 | Capture evidence and finalize submission | Student action |

## Definition of Done

- Acceptance criteria pass and input validation is present.
- Changes build with Maven and relevant JUnit/Selenium tests pass.
- Pipeline reports test results and does not deploy after a failed quality gate.
- Configuration is supplied through environment variables or Ansible, not hard-coded secrets.
- README and task documentation match the delivered behavior.
- Evidence is genuine and tied to the student's actual tool runs.
