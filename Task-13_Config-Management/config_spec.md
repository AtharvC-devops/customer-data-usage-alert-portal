# Configuration Management Specification

## Target

Disposable Ubuntu/Debian VM or Ubuntu on WSL2. The checked-in localhost inventory is for a local demonstration; do not run against a production server. With Docker Desktop WSL integration, leave `manage_docker_engine: false` (the default): Ansible uses the Docker daemon exposed to Ubuntu by Docker Desktop and does not install a second daemon. Set `manage_docker_engine: true` only on a native Linux host where this playbook should install and enable the `docker.io` service.

## Prerequisites and desired state

| Resource | Desired state |
|---|---|
| Packages | OpenJDK 17 runtime, Git, Maven, Python Docker SDK; Docker Engine only when `manage_docker_engine: true` |
| User | appuser exists and belongs to the docker group |
| Application folders | /opt/customer-usage-alert-portal; its data directory is owned by container UID/GID 10001 |
| Log folder | /var/log/customer-usage-alert-portal |
| Runtime configuration | SERVER_PORT=8085, database file under /data, ALERT_THRESHOLD_PERCENT=80 by default |
| Ports | 8085 published by the portal; 8080 is reserved if Jenkins shares the VM |
| Services | Docker Desktop daemon is used by default; native Docker service is enabled only when `manage_docker_engine: true`; portal container restarts unless stopped |
| Image | `customer-usage-alert-portal:local` by default; pre-build or pre-pull `app_image` before running the playbook |

## Idempotency

The Ansible playbook uses package state present, user/group state, directory state, optional Docker service state started, and a named container state started. It never pulls images; make the selected image available to the same daemon first. The `pull: never` setting requires community.docker 3.8.0 or newer, which is declared in requirements.yml. Run it a second time with identical variables; no configuration changes should be reported.

## Secrets

Do not put Docker Hub tokens or SSH private keys in inventory or playbook files. Configure private registry authentication through Jenkins credentials or an approved Ansible vault when needed.


