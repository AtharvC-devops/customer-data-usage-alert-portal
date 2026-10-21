# Viva Notes

## What is CI/CD?

Continuous Integration validates small changes frequently. Continuous Delivery/Deployment automates packaging and, when configured, release to an environment. Here Jenkins runs Maven and Selenium before optional Docker deployment.

## What is a Jenkinsfile?

A version-controlled pipeline definition. It records the checkout, build, test, report, image, and deployment stages so they can be reviewed with the application code.

## What are Dockerfile, image, and container?

A Dockerfile describes how to build an image. The image is the packaged application and runtime. A container is a running instance of that image with configuration and port/volume mappings.

## What is Ansible idempotency?

An idempotent playbook can run repeatedly with the same inputs while leaving a correct host unchanged. A second run should report no changes for the desired state.

## How does the delivery flow work?

Git changes are checked out by Jenkins. Maven runs unit and Selenium tests and publishes their results. Only a passing test stage can proceed to optional Docker build, publish, and deployment. Ansible configures the target and deploys the chosen image; the health endpoint checks the result.

## Why use synthetic data and H2?

Synthetic data avoids exposing real customer information. H2 provides a simple local demonstration database; a real service would use a secured managed database and access controls.

## How does rollback work?

The release uses immutable image tags. If the new deployment fails health validation, Ansible deploys the previously known-good tag and the operator checks health again.

