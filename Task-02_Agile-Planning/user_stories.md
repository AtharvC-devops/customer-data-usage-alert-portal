# User Stories and Acceptance Criteria

## US-01 — View the dashboard

As a customer operations agent, I want to see usage and alert summaries so that I can identify accounts that need attention.

- Given there are usage records, when I open the dashboard, then I see customer count, open alert count, average quota used, and the usage table.
- Given there are no matching search results, when I search, then the page explains that no records matched.

## US-02 — Record a usage event

As an operations agent, I want to add a usage event so that the dashboard reflects a newly reported measurement.

- Given valid customer, plan, period, usage, and quota fields, when I save, then the new record appears in the table.
- Given a negative usage or zero quota, when I submit the form or API request, then validation rejects the value.

## US-03 — Receive a usage alert

As a support representative, I want customers at or above 80% of their quota to appear in an alert list so that I can follow up before the limit is crossed.

- Given usage is below 80%, when the record is evaluated, then its status is Within limit.
- Given usage is from 80% to below 100%, then its status is Approaching limit and it appears in alerts.
- Given usage is 100% or more, then its status is Limit exceeded and it appears in alerts.

## US-04 — Search customer usage

As a network analyst, I want to search by customer name, ID, or plan so that I can review the relevant usage quickly.

- Given matching records, when I search for a partial name, ID, or plan, then matching rows are shown without case sensitivity.
- Given no matches, then the empty state is shown and no unrelated row is displayed.

## US-05 — Drill down into a customer

As a customer support representative, I want a detail view so that I can explain the customer's plan, usage, source, and status.

- Given a table row, when I select the customer, then the detail page shows the record and the threshold rule behind the status.
- Given an unknown record ID, then the application displays a clear not-found page.
