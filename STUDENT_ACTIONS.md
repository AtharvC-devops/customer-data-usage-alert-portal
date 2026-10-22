# Remaining steps requiring your account or machine

You confirmed that the local browser acceptance check is complete. The GitHub repository, main/develop branches, five backlog issues, repository/issue/Git-log/local-dashboard evidence, and the reviewed PR workflow are in place. PR #6 and PR #7 are merged, and issue #2 is closed.

The annotated `v1.0` tag exists locally and points to merged `develop` commit `72b7afb442ddbc88207ecde07713a84e78f8980d`. To publish the tag ref, run `git push origin refs/tags/v1.0` from this project folder. The Windows Git Credential Manager could not start in this session (`NtCreateDirectoryObject ... 0xC0000022`), so this one GitHub step needs your local Git session.

## Remaining CI/CD and provisioning actions

The current Windows session cannot access WSL (`Wsl/E_ACCESSDENIED`) and the Docker CLI has no reachable engine. The actual Jenkins, Docker container, and Ansible evidence must therefore come from a Linux VM or a working WSL/Docker setup.

1. Provide a Linux VM or enable a working WSL2 + Docker Desktop environment, then open this project folder there.
2. If you want Docker Hub publication, create a repository and add a Docker Hub personal access token directly to Jenkins as username/password credential ID `dockerhub-creds`. Do not paste the token into chat or commit it.
3. Run the prepared Jenkins pipeline and Ansible playbook using the commands in the task folders; capture the actual build/test runs, health check, and rollback output.
4. Ask the course supervisor to approve the scope only if your submission requires it.

All application code, tests, Jenkins pipeline, Docker/Compose configuration, Ansible playbook, deployment notes, and report are prepared. Local `mvn clean verify` passes and the Compose YAML parses. Do not submit generated/mock deployment screenshots; the remaining evidence must come from your actual services.