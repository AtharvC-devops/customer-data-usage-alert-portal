# Jenkins-Docker Continuous Deployment

The optional Jenkins stages run only after Maven and Selenium pass.

1. Create a Docker Hub repository named customer-usage-alert-portal.
2. In Jenkins, store the Docker Hub user and personal access token as a username/password credential with ID dockerhub-creds.
3. Set DOCKERHUB_REPO to YOUR-USERNAME/customer-usage-alert-portal.
4. Set ALERT_THRESHOLD_PERCENT to the warning level required (0–100; default 80).
5. Run with BUILD_IMAGE enabled to build an image tagged by Jenkins build number.
6. Enable PUSH_IMAGE to push the build-number tag and latest.
7. Enable DEPLOY_LOCAL to run the verified image on the Jenkins agent at port 8085.
8. Confirm Docker Hub shows the immutable build-number tag and the container returns healthy from `http://localhost:8085/actuator/health`.

For the first Jenkins run, use `BUILD_IMAGE=true`, `PUSH_IMAGE=false`, and `DEPLOY_LOCAL=false`. After that works, add the credential and registry repository, then enable `PUSH_IMAGE`. Enable `DEPLOY_LOCAL` only when the Jenkins agent owns the Docker daemon you intend to deploy to and port 8085 is available. A successful image build alone does not prove a push or deployment.

The Jenkinsfile never writes the token to a file. Docker logs and screenshots should not expose the credential value.


