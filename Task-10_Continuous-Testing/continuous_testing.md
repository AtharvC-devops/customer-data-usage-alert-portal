# Continuous Testing in Jenkins

The Jenkins Maven verification stage runs both the status-rule unit tests and Selenium WebDriver journeys. JUnit XML files are published with the pipeline junit step. Since Docker build, publish, and deploy stages follow verification, a failing test prevents deployment.

## Controlled defect drill

Use a temporary feature branch; do not merge it. This project's alert threshold is configurable, so the drill changes a test expectation rather than hard-coding an application threshold:

1. Create and push a short-lived branch from the latest `develop`.
2. In `src/test/java/edu/devops/usageportal/UsageStatusTest.java`, inside `classifiesUsageAtWarningThresholdAsWarning`, temporarily change the expected status from `UsageStatus.WARNING` to `UsageStatus.NORMAL`.
3. Commit and push that test-only change. In Jenkins, temporarily set the Pipeline job branch to `*/test/jenkins-failure-drill`, then build with all Docker parameters disabled. The status test should fail because 80% is a warning at the default threshold.
4. Save the failed Jenkins console and JUnit report. Restore the expected value to `UsageStatus.WARNING`, commit and push the correction, and rerun the same Jenkins branch. Confirm the test stage succeeds.
5. Restore the Pipeline job branch to `*/develop`. Do not merge the drill branch. Keep both actual build reports and the two commit IDs as evidence.

Do not merge the deliberate defect. Keep the failure and correction screenshots from your own run.


