# Jenkins Job Configuration

| Setting | Value |
|---|---|
| Job type | Pipeline |
| Display name | customer-usage-alert-portal |
| Definition | Pipeline script from SCM |
| SCM | Git |
| Repository URL | The real URL recorded in Task-04_Git-GitHub-Init/repo_url.md |
| Branch | develop |
| Script path | Jenkinsfile |
| Trigger | GitHub webhook if reachable; otherwise Poll SCM or manual |
| Build command | Maven clean verify |
| Published report | target/surefire-reports/*.xml |
| Archived artifact | target/customer-usage-alert-portal.jar |

For the first CI build, leave Docker parameters false. Jenkins itself needs Java 21 or newer; the Linux build agent needs Java 17 or newer and Maven available for this application. Docker credentials are needed only for PUSH_IMAGE. At the latest local verification, the Maven suite contained 11 tests.

