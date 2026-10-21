# Stakeholders

| Stakeholder | Need | Project involvement |
|---|---|---|
| Customer operations agent | Quickly find customers near a data limit | Main dashboard user; reviews alerts |
| Customer support representative | Explain the current usage and plan allowance | Opens customer detail and handles customer questions |
| Network/billing analyst | Spot patterns and verify usage events | Reviews summaries and source fields |
| Service administrator | Keep threshold and deployment configuration clear | Maintains environment values and access |
| DevOps engineer | Build, test, deploy, and recover the application predictably | Maintains Jenkins, Docker, and Ansible workflow |
| Customer | Receive fair, timely information about usage | Indirect beneficiary; no live customer data is stored |
| Course supervisor | Assess scope, implementation, and DevOps evidence | Reviews deliverables and viva explanation |

## Pain points addressed

1. Manual quota checks delay action when usage is rising quickly.
2. Separate usage lists make it difficult to see which records need attention first.
3. An alert without a customer detail view slows down support follow-up.
4. Inconsistent threshold handling can miss warnings or create confusing duplicate alerts.
5. Repeated manual deployment steps make the demo harder to reproduce.
