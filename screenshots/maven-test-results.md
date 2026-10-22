# Maven verification evidence

**Run:** 23 October 2026, from the `develop`-equivalent project checkout  
**Command:** `mvn -B clean verify`  
**Result:** BUILD SUCCESS — 11 tests, 0 failures, 0 errors, 0 skipped.  
**Coverage:** HtmlUnit app/form smoke, dashboard Selenium journey, UsageService search/summary, configurable threshold validation, and usage status boundaries.

The first clean integration run caught a stale one-argument `UsageService` constructor in `UsageServiceTest`, introduced when the configurable threshold constructor was added. The test now passes the documented default threshold (80%). The successful clean rerun and raw Surefire reports are saved in this folder. Atharv's local browser acceptance screenshots remain `local-dashboard.png` and `After Clicking on register.png`.