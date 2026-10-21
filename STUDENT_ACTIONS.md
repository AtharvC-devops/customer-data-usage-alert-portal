# Steps that need your account or machine

The application, tests, task documents, pipeline, Docker files, and Ansible files are prepared. These remaining steps interact with your GitHub/Docker Hub accounts or need software/services on your own machine. Capture genuine screenshots only after running each step.

## Local browser acceptance check

The automated HtmlUnit check exercises the packaged dashboard markup and form controls. Complete this live application check on your machine for the full search/save/detail journey:

1. From this project folder, run `mvn spring-boot:run` and open http://localhost:8085.
2. Search for `CUS-1003`; confirm Kabir Joshi remains and unrelated rows are filtered out.
3. Add a record with ID `CUS-23102C0016`, name `Atharv Chikane`, plan `Home Plus`, billing period `2026-10`, used `91`, quota `100`, and source `Manual demo entry`.
4. Confirm it appears as an approaching-limit alert, open its details, and confirm the usage, status, and source.
5. Save a real browser screenshot under `screenshots/local-dashboard.png`, then stop the app with Ctrl+C.

## GitHub repository, issues, branches, and pull request

1. Sign in to GitHub and create an empty public or private repository named customer-data-usage-alert-portal. Do not add a second README or license during creation.
2. Open this project folder in a terminal, configure your Git name/email if needed, then run:

       git init -b main
       git add .
       git commit -m "Add customer usage alert portal and DevOps project plan"
       git branch develop
       git remote add origin https://github.com/YOUR-USERNAME/customer-data-usage-alert-portal.git
       git push -u origin main
       git push -u origin develop

3. Replace the repository URL placeholder in Task-04_Git-GitHub-Init/repo_url.md and commit it.
4. Create issues from the templates in .github/ISSUE_TEMPLATE. The ready-to-file list is in Task-04_Git-GitHub-Init/issues_to_create.md.
5. Push feature/usage-dashboard and feature/alert-workflow branches, open pull requests to develop, review them, and merge. The branch practice is documented in Task-05_Feature-Branching/.
6. Capture the repository, issue list, PR review/merge, and git log in screenshots/ using the names listed in each task folder.

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
