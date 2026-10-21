# Jenkins-Docker Continuous Deployment

The optional Jenkins stages run only after Maven and Selenium pass.

1. Create a Docker Hub repository named customer-usage-alert-portal.
2. In Jenkins, store the Docker Hub user and personal access token as a username/password credential with ID dockerhub-creds.
3. Set DOCKERHUB_REPO to YOUR-USERNAME/customer-usage-alert-portal.
4. Run with BUILD_IMAGE enabled to build an image tagged by Jenkins build number.
5. Enable PUSH_IMAGE to push the build-number tag and latest.
6. Enable DEPLOY_LOCAL to run the verified image on the Jenkins agent at port 8085.
7. Confirm Docker Hub shows the tags and the container returns healthy.

The Jenkinsfile never writes the token to a file. Docker logs and screenshots should not expose the credential value.

