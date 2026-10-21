# Continuous Testing in Jenkins

The Jenkins Maven verification stage runs both the status-rule unit tests and Selenium WebDriver journeys. JUnit XML files are published with the pipeline junit step. Since Docker build, publish, and deploy stages follow verification, a failing test prevents deployment.

## Controlled defect drill

Use a temporary feature branch or disposable clone:

1. Change the warning ratio in UsageRecord from 0.80 to 0.90.
2. Push the temporary branch and run the Jenkins job. The test that expects warning at 80% should fail.
3. Capture the failed stage and published report.
4. Restore the value to 0.80, run Maven verification locally, commit the correction, and push the fix.
5. Rerun the pipeline and confirm the test stage is green before any image or deploy stage begins.

Do not merge the deliberate defect. Keep the failure and correction screenshots from your own run.

