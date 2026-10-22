# Remaining steps requiring your machine or account

This file separates completed repository work from steps that require your Windows/WSL machine or a service account. Do not mark a pending task complete until its real run output or screenshot has been saved.

## Already verified

- Atharv completed the local browser acceptance check.
- GitHub `main`, `develop`, and feature/fix branches exist. PRs [#6](https://github.com/AtharvC-devops/customer-data-usage-alert-portal/pull/6), [#7](https://github.com/AtharvC-devops/customer-data-usage-alert-portal/pull/7), and [#8](https://github.com/AtharvC-devops/customer-data-usage-alert-portal/pull/8) are merged into `develop`; issue #2 is closed. The PR #8 review is a maintainer COMMENT from the same GitHub account as the author, not an independent approval. GitHub has no CI checks configured.
- GitHub issues #1, #3, #4, and #5 remain open backlog items (CSV export, pagination, email notifications, authentication); they are outside the five-feature MVP.
- `mvn -B clean verify` passed locally on 23 October 2026: 11 tests, 0 failures, 0 errors, 0 skipped. Surefire summaries are in `screenshots/`.
- `docker compose config` parsed successfully. This checks Compose configuration only; no Docker Engine, image, container, Jenkins, or Ansible run has been verified from the Codex execution environment.
- The annotated local tag `v1.0` points to the PR #7 release commit `72b7afb442ddbc88207ecde07713a84e78f8980d`. It has not been pushed. PR #8 fixes a test constructor; product code at the release tag is unchanged.

## Single next action: enable Docker Desktop integration for Ubuntu

Open Docker Desktop, then go to **Settings → Resources → WSL Integration**, enable **Ubuntu**, and select **Apply & Restart**. This is the only immediate action. The Codex execution sandbox cannot reach your WSL distribution or Docker Engine, even though you have verified WSL interactively.

After Docker Desktop restarts, verify it from **Windows PowerShell**:

```powershell
wsl -l -v
docker version
```

`wsl -l -v` should show Ubuntu with VERSION `2`. `docker version` should show both Client and Server sections. Then open **Ubuntu** and run:

```bash
docker version
docker info
docker compose version
```

The Ubuntu commands should show a reachable Server and report Docker Compose v2. If `docker info` fails, capture the error text and leave the Jenkins/Docker deployment steps pending.

## Publish the existing v1.0 tag

In **Windows PowerShell**, from the project checkout, run:

```powershell
Set-Location 'C:\Users\athar\Documents\Codex\2026-10-21\https-github-com-chetan-katkar-devops\outputs\customer-data-usage-alert-portal'
git push origin refs/tags/v1.0
git ls-remote --tags origin 'refs/tags/v1.0*'
```

Expected push output includes `[new tag] v1.0 -> v1.0`; the `ls-remote` command should show the tag ref and, because it is annotated, its peeled commit ref. Sign in through the Git Credential Manager prompt if shown. Do not delete or recreate the tag.

## Task 11: build and verify the Docker deployment

In **Ubuntu**, change to the project folder (this checkout is on the Windows C: drive):

```bash
cd /mnt/c/Users/athar/Documents/Codex/2026-10-21/https-github-com-chetan-katkar-devops/outputs/customer-data-usage-alert-portal
docker compose config
docker compose build portal
docker compose up -d
docker compose ps
curl --fail http://localhost:8085/actuator/health
docker compose logs --tail 50 portal
```

Expected results: Compose validates, the `portal` container is running, and the health response contains `"status":"UP"`. Open `http://localhost:8085` in a browser and save genuine evidence under `screenshots/`. Lifecycle checks and safe cleanup are in `Task-11_Docker-Container/docker_commands.md`; `docker compose down` preserves the named data volume, while `docker compose down --volumes` deletes it.

## Tasks 7–10: Jenkins CI and continuous testing

Jenkins LTS currently requires Java 21 or newer. The application targets Java 17, so a Java 21 build agent can compile and test it. Before installing Jenkins in Ubuntu, check whether WSL has systemd:

```bash
ps -p 1 -o comm=
```

If it prints `systemd`, continue. Otherwise, open `/etc/wsl.conf` in Ubuntu with `sudo nano /etc/wsl.conf` and add the following section while preserving any existing settings:

```ini
[boot]
systemd=true
```

Save the file, exit Ubuntu, then run `wsl --shutdown` in **Windows PowerShell**. Reopen Ubuntu and verify `ps -p 1 -o comm=` prints `systemd`.

In **Ubuntu**, install Java, Maven, and Jenkins LTS using the current official repository instructions (the Jenkins signing key changes over time): [Jenkins Linux installation](https://www.jenkins.io/doc/book/installing/linux/).

```bash
sudo apt update
sudo apt install -y fontconfig openjdk-21-jdk maven wget
java -version
mvn -version
```

Then follow the official page's Debian/Ubuntu LTS repository steps, install `jenkins`, and start it:

```bash
sudo install -d -m 0755 /etc/apt/keyrings
sudo wget -O /etc/apt/keyrings/jenkins-keyring.asc https://pkg.jenkins.io/debian-stable/jenkins.io-2026.key
echo "deb [signed-by=/etc/apt/keyrings/jenkins-keyring.asc]" https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt update
sudo apt install -y jenkins
sudo systemctl enable --now jenkins
sudo systemctl status jenkins --no-pager
curl -I http://localhost:8080
```

Open `http://localhost:8080` in Windows, unlock using the password shown by `sudo cat /var/lib/jenkins/secrets/initialAdminPassword`, and install the suggested plugins. Do not send or screenshot the password.

Create a Jenkins **Pipeline** job named `customer-usage-alert-portal`:

- Definition: Pipeline script from SCM; SCM: Git.
- Repository: `https://github.com/AtharvC-devops/customer-data-usage-alert-portal.git`
- Branch specifier: `*/develop`; Script Path: `Jenkinsfile`.
- Leave Docker parameters disabled for the first build and select **Build Now** (or **Build with Parameters** with all Docker options false).

Expected result: `mvn -B clean verify` succeeds, JUnit reports are published, and `target/customer-usage-alert-portal.jar` is archived. Save the Jenkins build page and console output in `screenshots/`. A Jenkins run has not yet been performed.

For **Task 10**'s required failure/correction demo, use a short-lived branch and do not merge it. In Ubuntu:

```bash
git fetch origin develop
git switch -c test/jenkins-failure-drill origin/develop
```

In `src/test/java/edu/devops/usageportal/UsageStatusTest.java`, inside `classifiesUsageAtWarningThresholdAsWarning`, temporarily change the expected value from `UsageStatus.WARNING` to `UsageStatus.NORMAL`. Then:

```bash
git add src/test/java/edu/devops/usageportal/UsageStatusTest.java
git commit -m "test: demonstrate Jenkins failure gate"
git push -u origin test/jenkins-failure-drill
```

Set the Jenkins job branch to `*/test/jenkins-failure-drill`, build with Docker options disabled, and save the failed console and JUnit report. Restore the expected `UsageStatus.WARNING` in that same test method, then:

```bash
git add src/test/java/edu/devops/usageportal/UsageStatusTest.java
git commit -m "test: restore expected warning status"
git push
```

Rerun the job, save the green report, and restore the job branch to `*/develop`. Do not merge the drill branch. Details are in `Task-10_Continuous-Testing/continuous_testing.md`.

## Tasks 8 and 12: Jenkins Docker deployment

Only proceed after `docker info` succeeds in Ubuntu and Jenkins can use the same daemon. Check from Ubuntu:

```bash
sudo -u jenkins docker version
sudo -u jenkins docker info
```

If the Jenkins service user cannot access the daemon, stop here and resolve the Docker Desktop socket/group access before enabling pipeline image stages. Do not change socket permissions with `chmod 666`.

For registry push, create a Docker Hub repository named `customer-usage-alert-portal`. In Jenkins **Manage Jenkins → Credentials**, add a username/password credential with ID `dockerhub-creds`; use a Docker Hub access token as the password. Do not paste the token into chat or commit it. In a parameterized build, set `DOCKERHUB_REPO` to `YOUR-USERNAME/customer-usage-alert-portal`. First run with `BUILD_IMAGE=true`, `PUSH_IMAGE=false`, `DEPLOY_LOCAL=false`. Then test `PUSH_IMAGE=true`. Enable `DEPLOY_LOCAL=true` only when the Jenkins agent's Docker daemon is the intended target and port 8085 is free. Expected deployment health is `http://localhost:8085/actuator/health` with status `UP`. Save each actual build number, pushed tag, console, registry page, and health output.

## Tasks 13–14: Ansible provisioning, idempotency, and rollback

The playbook defaults to Docker Desktop's Ubuntu-integrated daemon and does not install a second Docker Engine. In Ubuntu, from the project root, install Ansible and the collection if needed:

```bash
sudo apt update
sudo apt install -y pipx python3-venv python3-docker
pipx install --include-deps ansible
export PATH="$HOME/.local/bin:$PATH"
ansible --version
ansible-galaxy collection install -r Task-13_Config-Management/requirements.yml
docker compose build portal
```

Run provisioning twice, saving both real outputs:

```bash
ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml --ask-become-pass 2>&1 | tee screenshots/ansible-first-run.log
ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml --ask-become-pass 2>&1 | tee screenshots/ansible-second-run.log
curl --fail http://localhost:8085/actuator/health | tee screenshots/ansible-health.txt
docker ps --filter name=customer-usage-alert-portal | tee screenshots/ansible-container.txt
```

Enter your Ubuntu sudo password only at the local prompt. The first run should create packages, the operator, directories, and the container. The unchanged second run should report `changed=0`; health should return `"status":"UP"`. If testing on a native Ubuntu VM that should receive its own Docker Engine, add `-e manage_docker_engine=true` to each Ansible command. Do not set that option in WSL when Docker Desktop integration is enabled.

If the Task 11 Compose container is still running, stop that Compose deployment first with `docker compose down`; Compose and Ansible use the same container name. Compose stores its data in a named volume, while the Ansible playbook uses `/opt/customer-usage-alert-portal/data`, so treat them as separate demo deployments.

For rollback, first push at least two immutable image tags through Jenkins, then use their real values below. The Ansible playbook does not pull images automatically:

```bash
docker pull YOUR-USERNAME/customer-usage-alert-portal:NEW_BUILD
ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml -e 'app_image=YOUR-USERNAME/customer-usage-alert-portal:NEW_BUILD' --ask-become-pass
curl --fail http://localhost:8085/actuator/health
docker pull YOUR-USERNAME/customer-usage-alert-portal:KNOWN_GOOD_BUILD
ansible-playbook -i Task-13_Config-Management/inventory.ini Task-13_Config-Management/setup.yml -e 'app_image=YOUR-USERNAME/customer-usage-alert-portal:KNOWN_GOOD_BUILD' --ask-become-pass
curl --fail http://localhost:8085/actuator/health
```

Save both deployment outputs and both health responses. Use actual tags from your Docker Hub repository; the placeholder names above are not real images. Follow `Task-14_Provisioning-Validation/rollback.md`.

## Final evidence and outstanding course action

Save genuine screenshots and raw command outputs under `screenshots/`, with the task number and date in each filename. Capture Jenkins success/failure/rerun, Docker image/container/health, Ansible first/second run, and rollback. Never fabricate evidence or include credentials. Ask the course supervisor to approve the scope only if your course requires it. The release summary and checkpoint tracker list the remaining tasks; tasks 7, 8, 10–14 stay unchecked until their external runs are recorded.

