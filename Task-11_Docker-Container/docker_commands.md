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


