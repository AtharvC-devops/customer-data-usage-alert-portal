# Maven verification evidence

**Run:** 23 October 2026, from the local `feature/alert-workflow` checkout

**Command:** `mvn -B clean verify`

**Result:** BUILD SUCCESS — 11 tests, 0 failures, 0 errors, 0 skipped.

**Coverage:** HtmlUnit app/form smoke, dashboard Selenium journey, UsageService search/summary, configurable threshold validation, and usage status boundaries.

The first clean integration run caught a stale one-argument `UsageService` constructor in `UsageServiceTest`, introduced when the configurable threshold constructor was added. The test now passes the documented default threshold (80%). The successful clean rerun and raw Surefire reports are saved in this folder. Atharv confirmed that local browser acceptance is complete; browser PNG screenshots were not present in the audited repository checkout.
