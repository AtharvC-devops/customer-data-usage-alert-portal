# Remaining steps requiring your account or machine

You confirmed that the local browser acceptance check is complete. The GitHub repository, main/develop branches, five backlog issues, and screenshots for the repository, issue list, Git log, and local dashboard are in place. PR #6 has been reviewed and merged. I am completing the second feature PR and its tag/evidence now.

The only actions that still depend on your computer/account are running the external Jenkins/Docker/Ansible toolchain and, if required, getting supervisor scope approval. The current Windows session cannot access WSL (`Wsl/E_ACCESSDENIED`) and the Docker CLI has no reachable engine, so those execution logs must come from a Linux VM or a working WSL/Docker setup.

## Minimum steps from your side

1. Provide a Linux VM or enable a working WSL2 + Docker Desktop environment, then open this project folder there. This is needed for the actual Jenkins agent, Docker container, and Ansible run evidence.
2. If you want Docker Hub publication, create a repository and add a Docker Hub personal access token directly to Jenkins as username/password credential ID `dockerhub-creds`. Do not paste the token into chat or commit it.
3. Run the prepared Jenkins pipeline and Ansible playbook using the commands in the task folders; capture the real first/second runs, health check, and rollback output.
4. Ask the course supervisor to approve the scope only if your submission requires it.

All application code, test cases, Jenkins pipeline, Docker/Compose configuration, Ansible playbook, deployment notes, and report are prepared. Local `mvn clean verify` passes and the Compose YAML parses. Do not submit generated/mock deployment screenshots; the remaining evidence must come from your actual services.