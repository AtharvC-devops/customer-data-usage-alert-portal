# Configuration Management Specification

## Target

Disposable Ubuntu/Debian VM or WSL Linux instance. The Ansible inventory can use localhost for a local demonstration. Do not run against a production server.

## Prerequisites and desired state

| Resource | Desired state |
|---|---|
| Packages | OpenJDK 17 runtime, Docker Engine, Git, Maven |
| User | appuser exists and belongs to the docker group |
| Application folders | /opt/customer-usage-alert-portal; its data directory is owned by container UID/GID 10001 |
| Log folder | /var/log/customer-usage-alert-portal |
| Runtime configuration | SERVER_PORT=8085, database file under /data, ALERT_THRESHOLD_PERCENT=80 by default |
| Ports | 8085 published by the portal; 8080 is reserved if Jenkins shares the VM |
| Services | Docker enabled and running; portal container restarted unless stopped |
| Image | app_image supplied as a versioned image, never only latest for rollback evidence |

## Idempotency

The Ansible playbook uses package state present, user/group state, directory state, Docker service state started, and a named container state started. Run it a second time with identical variables; no configuration changes should be reported.

## Secrets

Do not put Docker Hub tokens or SSH private keys in inventory or playbook files. Configure private registry authentication through Jenkins credentials or an approved Ansible vault when needed.

