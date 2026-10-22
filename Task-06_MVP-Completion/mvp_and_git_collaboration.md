# MVP Completion and Git Collaboration

## MVP status

| Capability | Result |
|---|---|
| Data/event entry | Valid events can be added through the form and REST API |
| Searchable dashboard | Search covers customer ID, name, and plan |
| Summary indicators | Customer count, alert count, average usage percentage |
| Status drill-down | Customer link opens a usage detail page |
| Alert/exception view | Configurable warning percentage (80% by default) and 100% exceeded states are highlighted |

## Second feature branch exercise

Use feature/alert-workflow for the alert endpoint and threshold handling. To demonstrate conflict resolution, make a small documentation-only change to the same sentence in Task-02_Agile-Planning/backlog.md on develop and feature/alert-workflow. Merge the feature branch into develop, resolve the conflict by keeping one consistent backlog entry, run Maven verification, and commit the resolution.

Keep this exercise in a disposable local clone or branch. Do not leave conflict markers in the release branch.

## Release baseline

After acceptance and tests pass, tag v1.0 locally. The pipeline and Docker instructions use immutable build-number tags for rollback; latest is only a convenience alias.

Actual PR, conflict, merge, and tag screenshots must be captured from the student's GitHub/terminal session.
