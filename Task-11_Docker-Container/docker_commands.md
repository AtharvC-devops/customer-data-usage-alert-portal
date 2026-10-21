# Docker Image and Container Lifecycle

Build an image from the project root:

    docker build -t customer-usage-alert-portal:1.0 .

Start the portal on port 8085 with a named data volume:

    docker run -d --name usage-alert -p 8085:8085 -v usage-alert-data:/data customer-usage-alert-portal:1.0

Inspect the image, container, application log, and health endpoint:

    docker image inspect customer-usage-alert-portal:1.0
    docker ps
    docker logs usage-alert
    curl http://localhost:8085/actuator/health

Exercise the requested lifecycle:

    docker stop usage-alert
    docker start usage-alert
    docker restart usage-alert
    docker rm -f usage-alert

The Dockerfile creates a non-root runtime user. The H2 data directory is mounted at /data so deleting the container does not delete the named volume.

