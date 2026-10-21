# Jenkins Installation and Continuous Integration Job

## Recommended setup

Use Jenkins LTS on Linux or WSL for this coursework. Install a JDK 17 and Maven 3.8+ on the Jenkins agent. The agent must check out the GitHub repository and run Maven. Docker access is needed only when image stages are enabled.

## Pipeline job

1. In Jenkins, create a Pipeline job named customer-usage-alert-portal.
2. Configure Pipeline script from SCM, select Git, provide the real repository URL, and select main or develop.
3. Set Script Path to Jenkinsfile.
4. Enable the GitHub webhook trigger if the Jenkins server is reachable from GitHub. For a local/private host, use Poll SCM or run the job manually.
5. Save and build with default parameters.
6. Confirm Maven verify passed and the jar was archived.

The Jenkinsfile publishes Surefire XML results even on a failed test and archives target/customer-usage-alert-portal.jar after a successful build. Test/deployment stages remain sequential, so deployment is skipped if verification fails.

## Evidence

Save Jenkins installation/agent view, job configuration, first successful build console, trigger evidence, and archived jar view in screenshots/. Do not include Jenkins secrets in screenshots.

