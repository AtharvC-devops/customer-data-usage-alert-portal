# Branch Policy

- main contains release-ready code and tagged versions.
- develop is the integration branch for reviewed work.
- feature/<short-name> is used for one focused change, for example feature/usage-dashboard or feature/alert-workflow.
- bugfix/<short-name> is used for a corrective change.
- Pull requests target develop, summarize the user-visible change, and include test results.
- Merge only after Maven verification passes and at least one review is recorded. For a solo coursework exercise, the author may self-review and note that clearly.
- Tag a completed local release as v1.0 after the MVP acceptance checks pass.

