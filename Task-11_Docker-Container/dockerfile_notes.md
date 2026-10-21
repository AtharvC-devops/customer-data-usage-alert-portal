# Dockerfile Notes

- Build stage uses Maven and JDK 17 to package the application.
- Runtime stage contains a JRE and the executable jar only.
- The runtime process uses UID/GID 10001 rather than root.
- Port 8085 is published for the embedded Tomcat server.
- /data is a volume mount for the H2 database file.
- Docker health checks call the actuator health endpoint.
- No registry token or customer data is baked into an image layer.

