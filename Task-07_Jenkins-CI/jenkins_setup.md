# Jenkins Installation and Continuous Integration Job

## Recommended setup

Use Jenkins LTS on Linux or WSL for this coursework. The Jenkins controller needs Java 21 or newer; the build agent needs JDK 17 or newer and Maven 3.8+ for this Java 17 application (JDK 21 also compiles the Java 17 target). The agent must check out the GitHub repository and run Maven. Docker access is needed only when image stages are enabled.

On Ubuntu, install the current Jenkins LTS package by following the official Jenkins Linux installation page: https://www.jenkins.io/doc/book/installing/linux/. Verify `java -version`, `sudo systemctl status jenkins`, and `curl -I http://localhost:8080` before creating the job.

## Pipeline job

1. In Jenkins, create a Pipeline job named customer-usage-alert-portal.
2. Configure Pipeline script from SCM, select Git, provide the real repository URL, and select `develop`.
3. Set Script Path to Jenkinsfile.
4. Enable the GitHub webhook trigger if the Jenkins server is reachable from GitHub. For a local/private host, use Poll SCM or run the job manually.
5. Save and build with default parameters.
6. Confirm Maven verify passed (11 tests at the latest local run) and the jar was archived.

The Jenkinsfile publishes Surefire XML results even on a failed test and archives target/customer-usage-alert-portal.jar after a successful build. Test/deployment stages remain sequential, so deployment is skipped if verification fails.

## Evidence

Save Jenkins installation/agent view, job configuration, first successful build console, trigger evidence, and archived jar view in screenshots/. Do not include Jenkins secrets in screenshots.


