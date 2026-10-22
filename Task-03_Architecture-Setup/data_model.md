# Data Model

## UsageRecord

| Field | Type | Rule / meaning |
|---|---|---|
| id | Long | Generated primary key |
| customerId | String | Required, max 40 characters |
| customerName | String | Required, max 100 characters |
| planName | String | Required, max 60 characters |
| billingPeriod | String | Required, max 20 characters; demo label such as YYYY-MM |
| usedGb | Decimal | Required, zero or greater |
| quotaGb | Decimal | Required, greater than zero |
| source | String | Optional, max 80 characters |
| recordedAt | Instant | Set at persistence time |
| status | Derived enum | NORMAL below the configured warning percentage (80% default), WARNING from that point, EXCEEDED at quota or higher |
| usagePercent | Derived decimal | usedGb / quotaGb × 100, rounded to one decimal |

Status and percentage are derived from the usage and quota values rather than stored as separate mutable fields.

