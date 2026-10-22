# Pipeline as Code and Server Deployment

The Jenkinsfile performs checkout, Maven clean verify, JUnit report publication, jar archiving, and optional Docker build/push/container deployment. Docker actions are off by default so a basic CI job works before registry credentials are added.

## Parameters

- BUILD_IMAGE builds a local image after verification.
- PUSH_IMAGE tags and pushes the image to Docker Hub using Jenkins credential ID dockerhub-creds.
- DEPLOY_LOCAL replaces the local customer-usage-alert-portal container after a passing test run.
- DOCKERHUB_REPO is the image namespace/repository, for example YOUR-USERNAME/customer-usage-alert-portal.

The embedded Tomcat web server listens on SERVER_PORT, default 8085. The container health check uses /actuator/health. Before enabling deploy, confirm that the Jenkins agent can access Docker and port 8085 is free.

## Deployment acceptance

1. Run a passing build with DEPLOY_LOCAL enabled.
2. Open http://localhost:8085.
3. Confirm the app health endpoint returns UP.
4. Save pipeline stage view, deployment console lines, browser dashboard, and health-check evidence.

For the first Jenkins job run, leave `BUILD_IMAGE`, `PUSH_IMAGE`, and `DEPLOY_LOCAL` disabled. Confirm the Maven test stage and archived jar first. Run Docker locally with the commands in `Task-11_Docker-Container/docker_commands.md`. Enable a Jenkins image or deployment parameter only after the Jenkins agent has access to the intended Docker daemon; record the actual build number and image tag.

