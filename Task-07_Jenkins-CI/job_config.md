# Jenkins Job Configuration

| Setting | Value |
|---|---|
| Job type | Pipeline |
| Display name | customer-usage-alert-portal |
| Definition | Pipeline script from SCM |
| SCM | Git |
| Repository URL | The real URL recorded in Task-04_Git-GitHub-Init/repo_url.md |
| Branch | main or develop |
| Script path | Jenkinsfile |
| Trigger | GitHub webhook if reachable; otherwise Poll SCM or manual |
| Build command | Maven clean verify |
| Published report | target/surefire-reports/*.xml |
| Archived artifact | target/customer-usage-alert-portal.jar |

For the first CI build, leave Docker parameters false. Configure a Linux/WSL Jenkins agent with Java 17 and Maven available. Docker credentials are needed only for PUSH_IMAGE.

