FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
RUN addgroup -S portal && adduser -S -G portal -u 10001 portal \
    && mkdir -p /data \
    && chown -R 10001:10001 /app /data
COPY --from=build --chown=10001:10001 /workspace/target/customer-usage-alert-portal.jar /app/app.jar
ENV SERVER_PORT=8085
ENV DB_URL=jdbc:h2:file:/data/usage-alert;AUTO_SERVER=TRUE
EXPOSE 8085
VOLUME ["/data"]
USER 10001:10001
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://127.0.0.1:8085/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
