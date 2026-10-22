# Remaining steps requiring your account or machine

You confirmed that the local browser acceptance check is complete. The GitHub repository, main/develop branches, five backlog issues, and screenshots for the repository, issue list, Git log, and local dashboard are already in place. I am handling the feature PR work.

Only the external CI/CD and provisioning steps remain here. Genuine evidence should be captured after running the actual services.

## Jenkins

1. Install Jenkins LTS on a Linux or WSL environment, install a JDK 17 and Maven tool, and ensure the Jenkins agent can access the repository and Docker daemon.
2. Add a Pipeline job pointing to this repository and set its script path to Jenkinsfile.
3. Add Docker Hub credentials as a Jenkins username/password credential with ID dockerhub-creds. Do not put the token in a file or in this repository.
4. Run once with defaults. Then enable BUILD_IMAGE. To publish, set PUSH_IMAGE and replace DOCKERHUB_REPO with your Docker Hub repository. To deploy on the Jenkins host, set DEPLOY_LOCAL and allow port 8085.
5. Capture the first successful Maven build, a deliberate test failure, the corrected rerun, archived jar, test report, and green end-to-end pipeline under screenshots/.

## Docker Hub

1. Create or sign in to Docker Hub and create a repository named customer-usage-alert-portal.
2. Add a personal access token to Jenkins under dockerhub-creds. Use a token rather than your account password.
3. Run the Jenkins pipeline with PUSH_IMAGE enabled. Confirm the build-number tag and latest tag appear in Docker Hub.
4. Save the registry page and running container health check screenshots.

## Ansible provisioning and rollback

1. Use a disposable Linux VM or WSL target. Install Ansible and Docker there; never run the course playbook against a production server.
2. Install the collection with ansible-galaxy collection install -r Task-13_Config-Management/requirements.yml.
3. Build or pull a tagged image, then set app_image in Task-13_Config-Management/inventory.ini or pass it as an Ansible extra variable.
4. Run ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml --ask-become-pass.
5. Run the same playbook again and save both outputs to demonstrate idempotency. Check http://localhost:8085/actuator/health.
6. Deploy a second tag, then restore the previous tag with the commands in Task-14_Provisioning-Validation/rollback.md. Capture both versions and health checks.

## Final evidence

Use the task-specific evidence lists in CHECKPOINTS.md. Do not submit generated mock screenshots; save screenshots from your actual browser, GitHub, Jenkins, Docker Hub, and terminal runs.
