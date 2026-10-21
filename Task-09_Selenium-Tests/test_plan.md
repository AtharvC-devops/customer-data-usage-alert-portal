# Selenium Test Plan

The automated suite uses Selenium WebDriver with headless HtmlUnit to exercise the dashboard markup and usage form fields in a clean Jenkins agent. Usage-status rules are covered by JUnit unit tests. The browser driver could not reliably navigate to the embedded Spring server in this workspace, so live search, save, and detail navigation remain part of the local manual check listed in STUDENT_ACTIONS.md.

| ID | User journey | Test | Assertions |
|---|---|---|---|
| WEB-01 | Open dashboard markup | UsageDashboardSeleniumTest.dashboardMarkupRendersAndUsageFormAcceptsInput | Dashboard title and sections render; Selenium can enter customer ID/name and the submit control is visible |
| WEB-02 | Live search/save/detail | Manual local-browser check | Search a seeded customer, save a 91/100 GB record, open its detail page, and confirm the source |
| RULE-01 | Below warning | UsageStatusTest.classifiesUsageBelowWarningThresholdAsNormal | NORMAL and calculated percent |
| RULE-02 | At warning | UsageStatusTest.classifiesUsageAtWarningThresholdAsWarning | WARNING at 80% |
| RULE-03 | At quota | UsageStatusTest.classifiesUsageAtQuotaAsExceeded | EXCEEDED at 100% |

## Run

Run mvn clean verify. Maven reports are written under target/surefire-reports. The HtmlUnit page test uses the packaged dashboard template through a data URL to avoid local socket dependencies. On an assertion failure, the Selenium test attempts to save a PNG under target/selenium-failures. HtmlUnit may not support image capture; the Surefire failure report remains available in that case.

