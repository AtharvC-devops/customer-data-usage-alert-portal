# Feature Branch Workflow

## First feature

The dashboard workflow includes the seeded usage table, summary indicators, search form, entry form, alert list, and a detail link. Implement the feature on feature/usage-dashboard, verify it with Maven, push it, and open a PR to develop.

## Reviewable change prepared

The current feature branch adds service-level tests for normalized dashboard search, sorting, distinct-customer summary counts, alert counts, and average usage. Run `mvn clean verify` before review; the current suite passes all seven tests.

Suggested commit: Add customer usage dashboard and search.

## Pull request review checklist

- The change matches the acceptance criteria in Task-02_Agile-Planning/user_stories.md.
- No real customer information or secrets were committed.
- The dashboard is usable at desktop and mobile widths.
- Maven unit and Selenium tests pass.
- Any added config variables are documented.

## Evidence to capture

- Git log showing the feature branch commit.
- GitHub PR page, review comment, and merged status.

See STUDENT_ACTIONS.md for the commands and account-specific steps.
