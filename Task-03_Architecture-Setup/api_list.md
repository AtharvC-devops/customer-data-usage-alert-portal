# REST API List

Base path: /api. JSON endpoints share server-side validation with the dashboard.

| Method | Path | Success | Purpose |
|---|---|---:|---|
| GET | /api/usages | 200 | List all usage records; optional q searches name, ID, or plan |
| GET | /api/usages/{id} | 200 | Read a record; unknown IDs return 404 |
| POST | /api/usages | 201 | Add a validated usage record |
| GET | /api/alerts | 200 | List WARNING and EXCEEDED records |
| GET | /api/summary | 200 | Return customer, alert, and average percentage totals |
| POST | /api/simulate | 201 | Add a synthetic event through the simulation endpoint |
| GET | /actuator/health | 200 | Deployment health endpoint |

Invalid JSON fields return a client validation response. No endpoint sends an email, SMS, or service-control command.

