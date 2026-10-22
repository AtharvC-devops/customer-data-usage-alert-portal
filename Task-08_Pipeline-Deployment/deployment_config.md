# Deployment Configuration

The course MVP is packaged as an executable Spring Boot jar. Its embedded Tomcat serves the web dashboard and REST API on SERVER_PORT, default 8085.

| Setting | Default | Purpose |
|---|---|---|
| SERVER_PORT | 8085 | HTTP listener and Docker port mapping |
| DB_URL | H2 file under data/ locally; /data in Docker | Embedded database location |
| ALERT_THRESHOLD_PERCENT | 80 | Usage percentage that starts a warning alert; configurable from 0 to 100 |
| DB_USERNAME | sa | H2 demonstration account |
| DB_PASSWORD | empty | H2 demonstration password |

The health endpoint is /actuator/health. The Jenkinsfile uses a container-local health polling loop after deploy. Production deployment requires proper secrets, network policy, persistent storage, authentication, and a managed database; those are outside this MVP.

