# Customer Data Usage Alert Portal — Checkpoint Tracker

**Student:** Atharv Chikane · **Roll No:** 23102C0016 · **Class:** BE CMPN-C  
**Project:** Jenkins Deployment for a Customer Data Usage Alert Portal

Your Excel row assigns the project topic under Task-1 and the technical workstreams from problem definition through provisioning under Tasks 2–15. The attached checkpoint example numbers problem definition as Task-1 and adds final-release documentation as Task-15. This repository follows the example's folder sequence for easier review; its Task-15_Final-Release-Docs folder is a wrap-up extra, while the Excel's last assigned workstream is provisioning and reliability validation in Task-14_Provisioning-Validation.

Checkboxes indicate repository artifacts prepared here. A checked item does not claim a GitHub, Jenkins, Docker Hub, or VM action occurred unless its evidence is saved in the relevant folder.

## TASK 1 — Problem Definition and Scope
**Folder:** Task-01_Problem-Definition/

- [x] Define the customer data usage alert problem and audience.
- [x] List target users, stakeholders, pain points, constraints, and measurable goals.
- [x] Freeze the five-feature MVP: data entry, searchable dashboard, summary indicators, detail drill-down, alert view.
- [ ] Review and approve the scope with the course supervisor.

**Deliverable:** Problem statement, stakeholder list, objectives, constraints, and MVP scope.

## TASK 2 — Agile Planning and DevOps Workflow
**Folder:** Task-02_Agile-Planning/

- [x] Write five user stories with acceptance criteria.
- [x] Prioritize the product backlog and create a 15-item task board.
- [x] Define the Definition of Done, sprint plan, and lifecycle diagram.

**Deliverable:** Stories, backlog, board, sprint plan, DoD, and lifecycle diagram.

## TASK 3 — Requirements, Architecture, and Technology Setup
**Folder:** Task-03_Architecture-Setup/

- [x] Document functional and non-functional requirements.
- [x] Create use-case and architecture diagrams.
- [x] Define the selected stack, data model, and REST API list.
- [x] Provide local setup and health-check instructions.
- [x] Implement the working Spring Boot application in src/.
- [x] Run the app locally and complete browser acceptance (completed by Atharv).
- [ ] Add a browser screenshot to this checkout if the course requires image evidence; no browser PNG is present under screenshots/ in the audited workspace.

**Deliverable:** SRS, diagrams, model/API notes, and a running local setup.

## TASK 4 — Git and GitHub Repository Initialization
**Folder:** Task-04_Git-GitHub-Init/

- [x] Add README, Maven project structure, .gitignore, and issue templates.
- [x] Define main, develop, and feature branch rules.
- [x] Prepare a meaningful first commit message and issue list.
- [x] Create the GitHub repository, push main/develop, create the five backlog issues, and save repository/issue evidence.

**Deliverable:** Repository URL, README, initial commit, issues, and branch policy. PR review/merge evidence is tracked under Task 5.

## TASK 5 — Feature Development with Branching
**Folder:** Task-05_Feature-Branching/

- [x] Implement dashboard, search, event entry, and detail workflow.
- [x] Document the feature-branch/PR review workflow.
- [x] Publish the feature branch, open a PR to develop, review it, merge it, and capture GitHub evidence (PR #6).

**Deliverable:** Working first feature, branch, PR review, and merge evidence.

## TASK 6 — MVP Completion and Git Collaboration
**Folder:** Task-06_MVP-Completion/

- [x] Implement all five MVP functions and alert thresholds.
- [x] Document a second feature branch, conflict-resolution exercise, and v1.0 tag.
- [x] Merge the second feature PR (#7), record the actual rebase conflict resolution, and create the v1.0 release tag; see screenshots/git-conflict-resolution.txt and the GitHub PR history.
- [x] Fix the stale UsageService test constructor in PR #8; latest clean local Maven run passes 11 tests.
- [ ] Publish the annotated v1.0 tag (still local-only; exact command is in STUDENT_ACTIONS.md).

**Deliverable:** Functional MVP, resolved conflict, tag, and updated backlog.

## TASK 7 — Jenkins Continuous Integration
**Folder:** Task-07_Jenkins-CI/

- [x] Provide Jenkins job setup instructions and pipeline definition.
- [x] Configure Maven verification, JUnit report publication, and jar archiving in Jenkinsfile.
- [ ] Install Jenkins, create the job, trigger a successful build, and save its console/build evidence.

**Deliverable:** Jenkins job, successful build, trigger proof, and archived artifact.

## TASK 8 — Pipeline as Code and Server Deployment
**Folder:** Task-08_Pipeline-Deployment/

- [x] Add checkout, build/test, package, and optional deployment stages to Jenkinsfile.
- [x] Parameterize application port and image repository.
- [ ] Run the pipeline and capture the deployed URL and configuration.

**Deliverable:** Jenkinsfile, pipeline run, application URL, and config evidence.

## TASK 9 — Selenium Test Design and Local Execution
**Folder:** Task-09_Selenium-Tests/

- [x] Write the automated dashboard/form smoke and threshold tests; document live search/save/detail checks.
- [x] Implement a Selenium WebDriver markup/form smoke test and failure screenshot capture.
- [x] Run `mvn -B clean verify` locally: 11 tests passed, 0 failures/errors/skips; save the Surefire summary.
- [x] Run the live search/save/detail checks in a local browser (completed by Atharv).
- [ ] Add the actual browser screenshot files to screenshots/ if required; the current checkout contains no browser PNGs.

**Deliverable:** Test plan, Selenium scripts, Maven report, and failure screenshot mechanism.

## TASK 10 — Continuous Testing in Jenkins
**Folder:** Task-10_Continuous-Testing/

- [x] Run the Selenium suite in the Jenkins Maven quality gate and publish Surefire reports.
- [x] Document a controlled failing-test/correction/rerun exercise.
- [ ] Demonstrate a failed build, fix the defect, rerun successfully, and save both reports/commits.

**Deliverable:** Jenkins test report, failed gate, fix commit, and green rerun.

## TASK 11 — Docker Image and Container Lifecycle
**Folder:** Task-11_Docker-Container/

- [x] Add a non-root multi-stage Dockerfile and persistent-data Compose setup.
- [x] Document image build, port mapping, logs, stop/start/restart, and removal commands.
- [x] Validate Compose configuration with `docker compose config`.
- [x] Build and launch the image with `docker compose build portal` and `docker compose up -d`.
- [x] Verify the container is running and healthy, and confirm the application and H2 startup in the logs.
- [x] Verify `/actuator/health` returns HTTP 200 and `status=UP`.
- [ ] Run the documented stop/start/restart/down lifecycle sequence; it is not yet verified.
- [ ] Capture a browser dashboard screenshot if required by the course; browser verification is not recorded.

**Verified execution (23 Oct 2026, run by Atharv in Ubuntu WSL2):** `docker compose config` passed; `docker compose build portal` succeeded; and `docker compose up -d` started the app. `customer-usage-alert-portal` is running and healthy from image `customer-usage-alert-portal:local`, with port 8085 published. Atharv confirmed in the application logs that Spring Boot 3.3.5 started on Java 17 and H2 connected at `jdbc:h2:file:/data/usage-alert`. `/actuator/health` returned HTTP 200 with `{"status":"UP","groups":["liveness","readiness"]}`. Raw container and health outputs are saved as `screenshots/task-11-containers.txt`, `screenshots/task-11-container-health.txt`, and `screenshots/task-11-health.txt`; a raw application log file is not present in the evidence folder. The browser dashboard has not been verified or captured. The Codex Windows shell cannot access Docker Desktop's named pipe; this does not affect the Ubuntu run reported above.

**Deliverable:** Dockerfile, image details, command log, and running-container evidence.

## TASK 12 — Jenkins-Docker Continuous Deployment
**Folder:** Task-12_Jenkins-Docker-CD/

- [x] Add versioned image build, optional Docker Hub push, and container deployment stages.
- [x] Document Jenkins credential setup without storing secrets in source.
- [ ] Push an image and verify the running container from a successful Jenkins pipeline.

**Deliverable:** Versioned registry image and commit-to-container evidence.

## TASK 13 — Configuration Management
**Folder:** Task-13_Config-Management/

- [x] Document packages, user, folders, environment, ports, and services.
- [x] Add Ansible inventory, collection requirements, and idempotent playbook.
- [ ] Run the playbook on a disposable Linux/WSL target and save the first execution evidence.

**Deliverable:** Configuration specification, Ansible files, and execution log.

## TASK 14 — Provisioning and Reliability Validation
**Folder:** Task-14_Provisioning-Validation/

- [x] Provide clean provisioning, idempotency, health-check, and rollback procedures.
- [ ] Provision a clean target, run twice, confirm health, test rollback, and capture results.

**Deliverable:** Provisioned app, idempotent run, health check, and rollback proof.

## EXTRA WRAP-UP — Final Release and Viva Preparation
**Folder:** Task-15_Final-Release-Docs/

- [x] Prepare the technical report, troubleshooting guide, release summary, and viva notes as a helpful submission package following the attached checkpoint example.
- [x] Summarize the end-to-end flow and limitations.
- [ ] Add genuine screenshots after executing the external toolchain.
- [ ] Prepare the viva presentation from the supplied outline if your course requires a slide deck.

**Deliverable:** Final report, release notes, troubleshooting guide, and viva materials.

## Evidence filenames

Raw Surefire reports and the Compose configuration output are in screenshots/. The audited checkout contains no browser, Jenkins, Docker Hub, or Ansible screenshots yet. Add genuine evidence after running those services.
