# Docker Image and Container Lifecycle

Run the following from Ubuntu after Docker Desktop WSL integration is enabled. From the repository root, build the Compose image and start the app:

    docker compose config
    docker compose build portal
    docker compose up -d

Compose builds `customer-usage-alert-portal:local`, publishes port 8085, and keeps H2 data in the `usage-alert-data` named volume. Confirm the actual container, health endpoint, and logs:

    docker image inspect customer-usage-alert-portal:local
    docker compose ps
    docker compose logs --tail 50 portal
    curl --fail http://localhost:8085/actuator/health

Exercise the requested lifecycle:

    docker compose stop
    docker compose start
    docker compose restart
    docker compose down

`docker compose down` removes the container and network but keeps `usage-alert-data`; do not add `--volumes` if you want to preserve its data. Capture real terminal output and a screenshot of the app only after these commands succeed.

The Dockerfile creates a non-root runtime user. The H2 data directory is mounted at /data so deleting the container does not delete the named volume.

## Verified deployment result

Atharv verified the Compose deployment in Ubuntu WSL2 on 23 October 2026. `docker compose config` passed, `docker compose build portal` succeeded, and `docker compose up -d` started `customer-usage-alert-portal`; `docker compose ps` and Docker inspection showed it running and healthy with image `customer-usage-alert-portal:local` and port 8085 published. Application logs confirmed Spring Boot 3.3.5 started on Java 17 and H2 connected using `jdbc:h2:file:/data/usage-alert`. `curl -i --max-time 10 http://localhost:8085/actuator/health` returned HTTP 200 and `{"status":"UP","groups":["liveness","readiness"]}`.

Saved raw container and health outputs: `screenshots/task-11-containers.txt`, `screenshots/task-11-container-health.txt`, and `screenshots/task-11-health.txt`. The application log output itself has not been saved. The browser dashboard has not been verified or captured. This records the local project container and health endpoint only; it does not verify Jenkins deployment, Docker Hub publishing, Ansible provisioning, or the lifecycle commands listed above.

