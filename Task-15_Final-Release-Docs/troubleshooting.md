# Troubleshooting

| Symptom | Likely cause | Check / fix |
|---|---|---|
| Maven cannot resolve dependencies | Network or repository access unavailable | Check Maven proxy/mirror and retry mvn -U clean verify |
| Port 8085 is already in use | Another app/container owns the port | Stop it or set SERVER_PORT to another port |
| Docker daemon connection fails | Docker Desktop/Engine is stopped or Jenkins lacks socket access | Start the daemon and grant the agent Docker access |
| Jenkins cannot find Maven | Maven is missing on the agent PATH | Install Maven or configure the global tool and adjust Jenkinsfile |
| Jenkins tests pass but no deploy occurs | Docker parameters are disabled | Enable BUILD_IMAGE and, as needed, PUSH_IMAGE or DEPLOY_LOCAL |
| Docker Hub push is denied | Wrong repository name or credential | Verify DOCKERHUB_REPO and dockerhub-creds; use an access token |
| Container starts but page is unavailable | Port mapping or container status is wrong | Check docker ps, docker logs, and published port 8085 |
| Ansible cannot find docker_container | Collection is not installed on the control node | Run ansible-galaxy collection install -r requirements.yml |
| Ansible image pull fails | Tag is not pushed or registry is private | Push the tag and authenticate the target; avoid using latest for rollback |
| Second Ansible run changes the container | Image/pull state or variables differ | Use the same versioned image and identical variables |

